package logic;

import data.FileReader;
import data.GameParam;
import data.ScoreWritter;
import logic.dijkstraAI.Maze;
import logic.dijkstraAI.Node;

import java.util.ArrayList;

/**
 * Cette classe modélise une partie du jeu PacMan.
 *
 * @author Théophile Chénais
 * @inv {@code this.life >=0}
 * @inv {@code this.gameBoard != null}
 * @inv {@code this.gameBoard.width > 0}
 * @inv {@code this.gameBoard.height > 0}
 * @inv {@code this.score >= 0}
 * @inv {@code this.powerDuration > 0}
 */
public class Game {

    /**
     * Entier représentant le nombre de vie de PacMan
     */
    private int life;
    /**
     * Booléen indiquant si PacMan a un pouvoir ou non
     */
    private boolean power;
    /**
     * Entier contenant le score de la partie
     */
    private int score;
    /**
     * Score finale
     */
    private int finalScore;
    /**
     * Compteur de combos fantômes mangés
     */
    private int comboCount;
    /**
     * Durée du pouvoir
     */
    private int powerDuration;
    /**
     * Tableau des éléments du jeux (mur et PacDot)
     */
    private GamePiece[][] gameBoard;
    /**
     * Tableau des fantômes
     */
    private Ghost[][] gameGhostBoard;
    /**
     * Objet permettant d'utilisé l'algorithme de recherche de chemin Dijkstra
     */
    private Maze[][] mazeTab;
    /**
     * Liste des fantômes
     */
    private ArrayList<Ghost> ghostList = new ArrayList<>();
    /**
     * Objet PacMan
     */
    private PacMan pacMan;
    /**
     * Dernier déplacement de pacMan en x
     */
    private int previousDX;
    /**
     * Dernier déplacement de pacMan en y
     */
    private int previousDY;
    private int pacDotCount = 0;
    private boolean finished = false;

    /**
     * Construit une partie
     *
     * @param level niveau de la partie
     * @pre level est un niveau qui existe
     * @post life = 3
     * @post score = 0
     * @post countGhost = 1
     * @post power = false
     */
    public Game(int level) {
        FileReader in = new FileReader("res/Levels/Level" + level + ".json");
        GameParam gameParam = in.initGame(level);
        if (gameParam != null) {
            this.life = 3;
            this.score = 0;
            this.finalScore = 0;
            this.comboCount = 1;
            this.power = false;
            Ghost.countGhost = 0;
            this.powerDuration = gameParam.getPowerTime();
            this.gameBoard = new GamePiece[gameParam.getBoard().length][gameParam.getBoard()[0].length];
            this.gameGhostBoard = new Ghost[gameParam.getBoard().length][gameParam.getBoard()[0].length];
            for (int i = 0; i < gameParam.getBoard().length; i++) {
                for (int j = 0; j < gameParam.getBoard()[0].length; j++) {
                    switch (gameParam.getBoard()[i][j]) {
                        case 1:
                            this.gameBoard[i][j] = new Wall();
                            break;
                        case 2:
                            this.gameBoard[i][j] = new PacDot(gameParam.getPacDotValue());
                            this.pacDotCount++;
                            break;
                        case 3:
                            this.gameBoard[i][j] = new Fruit(gameParam.getLevel(), gameParam.getFruitValue());
                            break;
                        case 4:
                            this.gameBoard[i][j] = new SuperPacDot();
                            this.pacDotCount++;
                            break;
                        case 5:
                            Ghost g = new Ghost(gameParam.getGameSpeed(), gameParam.getStartGhostX(), gameParam.getStartGhostY(), i, j);
                            this.gameGhostBoard[i][j] = g;
                            this.ghostList.add(g);
                            Ghost.countGhost++;
                            break;
                        case 6:
                            this.pacMan = new PacMan(gameParam.getGameSpeed(), i, j);
                    }
                }
            }
        }
        mazeTab = new Maze[gameParam.getBoard().length][gameParam.getBoard()[0].length];
        for (int i = 0; i < gameParam.getBoard().length; i++) {
            for (int j = 0; j < gameParam.getBoard()[0].length; j++) {
                if (gameParam.getBoard()[i][j] != 1) {
                    mazeTab[i][j] = new Maze(gameParam.getBoard(), i, j);
                }
            }
        }
    }

    /**
     * Jeu du PacMan, choix du déplacement (dx, dy)
     *
     * @param mouvement paramètre tempraire
     */
    public synchronized void play(int mouvement) {
        int dx = 0;
        int dy = 0;
        switch (mouvement) {
            case 1: // haut
                dx = -1;
                dy = 0;
                break;
            case 2: // Bas
                dx = 1;
                dy = 0;
                break;
            case 3: // Gauche
                dx = 0;
                dy = -1;
                break;
            case 4: // Droite
                dx = 0;
                dy = 1;
                break;
            default:
                break;
        }
        if (this.isValidBoardMove(this.pacMan.getX(), this.pacMan.getY(), dx, dy)) {
            movePacMan(dx, dy);
            this.previousDX = dx;
            this.previousDY = dy;
        } else {
            if (this.isValidBoardMove(this.pacMan.getX(), this.pacMan.getY(), this.previousDX, this.previousDY)) {
                movePacMan(this.previousDX, this.previousDY);
            }
        }
    }

    /**
     * Jeu des fantomes, choix du déplacement (dx, dy)
     *
     * @param ghost le fantôme concerné
     * @pre g != null
     */
    public void play(Ghost ghost) {
        int dx = 0;
        int dy = 0;
        int[] movement = new int[2];
        if (ghost.isStateEaten()) { // Retour au départ
            movement = getMovement(ghost.getX(), ghost.getY(), Ghost.getStartX(), Ghost.getStartY());
            if (movement != null) {
                dx = movement[0];
                dy = movement[1];
            } else {
                System.out.println("Ghost eaten -> movement is null");
            }
        } else if (this.power || ghost.getName().equals(GhostNames.Pinky.toString()) || ghost.getName().equals(GhostNames.Inky.toString()) || ghost.getName().equals(GhostNames.Clyde.toString())) { // En danger
            int endX = 0;
            int endY = 0;
            while (this.gameBoard[endX][endY] instanceof Wall) {
                endX = (int) (Math.random() * (this.gameBoard.length-1) + 1);
                endY = (int) (Math.random() * (this.gameBoard[0].length-1) + 1);
            }
            movement = getMovement(ghost.getX(), ghost.getY(), endX, endY);
            if (movement != null) {
                dx = movement[0];
                dy = movement[1];
            } else {
                System.out.println("Fantômes effrayés + fantôme autre que G1 -> movement is null");
            }
        } else if (ghost.getName().equals(GhostNames.Blinky.toString())) { // Poursuite
            movement = getMovement(ghost.getX(), ghost.getY(), this.pacMan.getX(), this.pacMan.getY());
            if (movement != null) {
                dx = movement[0];
                dy = movement[1];
            } else {
                System.out.println("Fantôme G1 -> movement is null");
            }
        }
        System.out.println("dx: " + dx + " dy: " + dy);
        if (this.isValidBoardMove(ghost.getX(), ghost.getY(), dx, dy)) {
            System.out.println();
            moveGhost(ghost, dx, dy);
        }
    }

    /**
     * Vérifie qu'un mouvement n'est que d'une case et pas en diagonale
     *
     * @param dx déplacement sur les x
     * @param dy déplacement sur les y
     * @return vrai si le déplacement est valide, false dans le cas contraire
     */
    private boolean isValidMove(int dx, int dy) {
        boolean ret = false;
        if ((Math.abs(dx) == 1 && dy == 0) || (dx == 0 && Math.abs(dy) == 1)) {
            ret = true;
        }
        return ret;
    }

    /**
     * Vérifie si le mouvement est valide sur le plateau (pas dans un mur, etc)
     *
     * @param x  position en x de l'élément à déplacer
     * @param y  position en y de l'élément à déplacer
     * @param dx déplacement en x à effectuer
     * @param dy déplacement en y à effectuer
     * @return vrai si le déplacement est valide, false dans le cas contraire
     */
    private boolean isValidBoardMove(int x, int y, int dx, int dy) {
        boolean result = false;
        if (this.isValidMove(dx, dy)) {
           if (x+dx >= 0 && y+dy >= 0 && x+dx < this.gameBoard.length && y+dy < this.gameBoard[0].length && !(this.gameBoard[x+dx][y+dy] instanceof Wall)) {
               result = true;
           }
        }
        return result;
    }

    /**
     * Déplacement du PacMan
     *
     * @param dx déplacement en x de PacMan
     * @param dy déplacement en y de PacMan
     */
    private void movePacMan(int dx, int dy) {

        int x = this.pacMan.getX();
        int y = this.pacMan.getY();

        if (y == 0 && dy == -1) {
            dy = this.gameBoard[0].length - 1;
        } else if (y == this.gameBoard[0].length - 1 && dy == 1) {
            dy = -(this.gameBoard[0].length - 1);
        } else if (x == 0 && dx == -1) {
            dx = this.gameBoard.length - 1;
        } else if (x == this.gameBoard.length - 1 && dx == 1) {
            dx = -(this.gameBoard.length - 1);
        }

        if (this.gameGhostBoard[x + dx][y + dy] != null && !this.gameGhostBoard[x + dx][y + dy].isStateEaten()) {
            if (this.power) {
                killGhost(x + dx, y + dy);
                this.pacMan.setX(x + dx);
                this.pacMan.setY(y + dy);
            } else {
                killPacMan();
            }
        } else {
            if (this.gameBoard[x + dx][y + dy] instanceof SuperPacDot) {
                this.power = true;
                this.comboCount = 1;
                this.pacDotCount--;
                this.finished = (pacDotCount == 0);
            } else if (this.gameBoard[x + dx][y + dy] instanceof Fruit) {
                this.score += Fruit.value;
                this.finalScore += Fruit.value;
                this.updateLifeCount();
            } else if (this.gameBoard[x + dx][y + dy] instanceof PacDot) {
                this.score += PacDot.value;
                this.finalScore += PacDot.value;
                this.pacDotCount--;
                this.updateLifeCount();
                this.finished = (pacDotCount == 0);
            }
            this.pacMan.setX(x + dx);
            this.pacMan.setY(y + dy);

        }
        this.gameBoard[this.pacMan.getX()][this.pacMan.getY()] = null;
    }

    /**
     * Déplace un fantôme
     *
     * @param ghost fantôme à déplacer
     * @param dx    déplacement en x à effectuer
     * @param dy    déplacement en y à effectuer
     * @pre ghost != null
     * @pre dx isValidMoveGhost
     * @pre dy isValidMoveGhost
     * @post ghost.x = x+dx
     * @post ghost.y = y+dy
     * @post ghostboard[x+dx][y+dy] = ghost
     * @post ghostboard[x][y] = null
     */
    private void moveGhost(Ghost ghost, int dx, int dy) {
        int x = ghost.getX();
        int y = ghost.getY();

        if (y == 0 && dy == -1) {
            dy = this.gameBoard[0].length - 1;
        } else if (y == this.gameBoard[0].length - 1 && dy == 1) {
            dy = -(this.gameBoard[0].length - 1);
        } else if (x == 0 && dx == -1) {
            dx = this.gameBoard.length - 1;
        } else if (x == this.gameBoard.length - 1 && dx == 1) {
            dx = -(this.gameBoard.length - 1);
        }

        ghost.setX(x + dx);
        ghost.setY(y + dy);
        if (this.gameGhostBoard[x + dx][y + dy] == null) {
            this.gameGhostBoard[x + dx][y + dy] = ghost;
            this.gameGhostBoard[x][y] = null;
        } else {
            this.gameGhostBoard[x][y] = null;
        }
    }

    private int[] getMovement(int xStart, int yStart, int xEnd, int yEnd) {
        int[] movement = new int[2];
        if (this.mazeTab == null) {
            System.out.println("Aïe");
        }
        if (this.mazeTab[xStart][yStart] != null) {
            Node node = this.mazeTab[xStart][yStart].getPath(xEnd, yEnd);
            if (node != null && node.getShortestPath().size() > 1) {
                movement[0] = Integer.parseInt(node.getShortestPath().get(1).getName().split(":")[0]) - xStart;
                movement[1] = Integer.parseInt(node.getShortestPath().get(1).getName().split(":")[1]) - yStart;
            } else if (Math.abs(xEnd - xStart + yEnd - yStart) == 1) {
                movement[0] = xEnd - xStart;
                movement[1] = yEnd - yStart;
            } else {
                System.out.println("Impossible node null");
                movement = null;
            }
        } else {
            System.out.println("Impossible maze null");
            movement = null;
        }
        return movement;
    }

    /**
     * Evenement lorsque PacMan mange un fantôme
     *
     * @param x position du fantôme mangé
     * @param y position du fantôme mangé
     */
    public void killGhost(int x, int y) {
        this.gameGhostBoard[x][y].setStateEaten(true);
        this.score += Ghost.getValue() * comboCount;
        this.updateLifeCount();
        this.comboCount += 1;
    }

    /**
     * Evenement lorsque PacMan est mangé
     */
    public void killPacMan() {
        this.life -= 1;
        if (this.life < 0) {
            end();
        }
        this.previousDX = 0;
        this.previousDY = 0;
        this.pacMan.setX(this.pacMan.getStartX());
        this.pacMan.setY(this.pacMan.getStartY());
    }


    /**
     * Augmente le nombre de vie à chaque tranche de 10k points
     */
    private void updateLifeCount() {
        if (this.score >= 10000) {
            this.life += 1;
            this.score = 0;
        }
    }

    /**
     * Affiche le plateau de jeu et ses éléments dans la console
     */
    public void displayBoard() {
        this.gameBoard[this.pacMan.getX()][this.pacMan.getY()] = this.pacMan;
        for (int i = 0; i < this.gameBoard.length; i++) {
            for (int j = 0; j < this.gameBoard[0].length; j++) {
                GamePiece g = this.gameBoard[i][j];
                Ghost gh = this.gameGhostBoard[i][j];
                if (gh != null) {
                    System.out.print(/*gh.getName()*/"\033[31m" + "GH" + "\033[39m" + "  ");
                } else {
                    if (g != null) {
                        System.out.print(g.getName() + "  ");
                    } else {
                        System.out.print("00" + "  ");
                    }
                }
            }
            System.out.println("");
        }
        this.gameBoard[this.pacMan.getX()][this.pacMan.getY()] = null;
    }

    public void writeScore(String name) {
        ScoreWritter.writeScore(name, this.finalScore);
    }

    /**
     * Fin
     */
    private void end() {
        finished = true;
    }

    /**
     * Retoune le nombre de point de vie restant
     *
     * @return un entier correspondant au point de vie restant à PacMan
     * @post result = this.life
     */
    public int getLife() {
        return this.life;
    }

    /**
     * Modifier le nombre de point de vie
     *
     * @param life un entier positif remplacant le nombre de point de vie de PacMan
     * @pre {@code life >= 0}
     * @post this.life = life
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * Retourne true si PacMan a un pouvoir, false sinon
     *
     * @return un booléen indiquant si PacMan possede un pouvoir
     * @post result = power
     */
    public boolean isPower() {
        return this.power;
    }

    /**
     * Modifie l'état du pouvoir de PacMan
     *
     * @param power un booléen indiquant si PacMan possède un pouvoir
     * @post this.power = power
     */
    public void setPower(boolean power) {
        this.power = power;
    }

    /**
     * Retourne le score
     *
     * @return une entier représentant le score
     * @post result = score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Modifie la valeur du score
     *
     * @param score un entier
     * @pre {@code score >= 0}
     * @post this.score = score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Retourne le score finale
     *
     * @return le score finale
     * @post result = finalScore
     */
    public int getFinalScore() {
        return finalScore;
    }

    /**
     * Modifie le score finale
     *
     * @param finalScore new finalScore
     * @post this.finalScore = finalScore
     */
    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    /**
     * Retourne la valeur du compteur de combo
     *
     * @return comboCount
     * @post result = comboCout
     */
    public int getComboCount() {
        return comboCount;
    }

    /**
     * Modifie le compteur de combo
     *
     * @param comboCount new comboCount
     * @post this.comboCount = comboCount
     */
    public void setComboCount(int comboCount) {
        this.comboCount = comboCount;
    }

    /**
     * Retourne le tableau à deux dimensions des pièces
     *
     * @return gameBoard
     * @post result = gameBoard
     */
    public GamePiece[][] getGameBoard() {
        return gameBoard;
    }

    /**
     * Modifie la gameBoard
     *
     * @param gameBoard new gameBoard
     * @post this.gameBoard = gameBoard
     */
    public void setGameBoard(GamePiece[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Retourne le plateau des fantômes
     *
     * @return gameGhostBoard
     */
    public Ghost[][] getGameGhostBoard() {
        return gameGhostBoard;
    }

    /**
     * Modifie le plateau des fantômes
     *
     * @param gameGhostBoard new gameGhostBoard
     * @post this.gameGhostBoard = gameGhostBoard
     */
    public void setGameGhostBoard(Ghost[][] gameGhostBoard) {
        this.gameGhostBoard = gameGhostBoard;
    }

    /**
     * Retourne le PacMan
     *
     * @return pacMan
     * @post result = pacMan
     */
    public PacMan getPacMan() {
        return pacMan;
    }

    /**
     * Modifie le PacMan
     *
     * @param pacMan new pacMan
     * @post this.pacMan = pacMans
     */
    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    /**
     * Retourne la liste des fantômes
     *
     * @return ghostList
     * @post result = ghostList
     */
    public ArrayList<Ghost> getGhostList() {
        return ghostList;
    }

    /**
     * Modifie la liste des fantômes
     *
     * @param ghostList new ghostList
     * @post this.ghostList = ghostList
     */
    public void setGhostList(ArrayList<Ghost> ghostList) {
        this.ghostList = ghostList;
    }

    /**
     * Retourne le dernier déplacement en x de PacMan
     *
     * @return previousDX
     * @post result = previousDX
     */
    public int getPreviousDX() {
        return previousDX;
    }

    /**
     * Modifie le dernier déplacement en x de PacMan
     *
     * @param previousDX new previousDX
     * @post this.previousDX = previousDX
     */
    public void setPreviousDX(int previousDX) {
        this.previousDX = previousDX;
    }

    /**
     * Retourne le dernier déplacement en y de PacMan
     *
     * @return previousDY
     * @post result = previousDY
     */
    public int getPreviousDY() {
        return previousDY;
    }

    /**
     * Modifie le dernier déplacement en y de PacMan
     *
     * @param previousDY new previousDY
     * @post this.previousDY = previousDY
     */
    public void setPreviousDY(int previousDY) {
        this.previousDY = previousDY;
    }

    /**
     * Retourne la durée du pouvoir des Super PacDots
     *
     * @return powerDuration
     * @post result = powerDuration
     */
    public int getPowerDuration() {
        return powerDuration;
    }

    /**
     * Modifie la durée des pouvoirs des Super PacDots
     *
     * @param powerDuration new powerDuration
     * @post this.powerDuration = powerDuration
     */
    public void setPowerDuration(int powerDuration) {
        this.powerDuration = powerDuration;
    }

    public Maze[][] getMazeTab() {
        return mazeTab;
    }

    public void setMazeTab(Maze[][] mazeTab) {
        this.mazeTab = mazeTab;
    }

    public int getPacDotCount() {
        return pacDotCount;
    }

    public void setPacDotCount(int pacDotCount) {
        this.pacDotCount = pacDotCount;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

