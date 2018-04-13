package logic;

import data.FileReader;
import data.GameParam;

import java.util.ArrayList;

/**
 *
 * Cette classe modélise une partie du jeu PacMan.
 * @author Théophile Chénais
 * @inv {@code this.life >=0}
 * @inv {@code this.gameBoard != null}
 * @inv {@code this.gameBoard.width > 0}
 * @inv {@code this.gameBoard.height > 0}
 * @inv {@code this.score >= 0}
 * @inv {@code this.powerDuration > 0}
 *
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

    /**
     * Construit une partie
     * @param level niveau de la partie
     * @pre  level est un niveau qui existe
     * @post life = 3
     * @post score = 0
     * @post countGhost = 1
     * @post power = false
     */
    public Game(int level) {
        FileReader in = new FileReader("res/Levels/Level"+level+".json");
        GameParam gameParam = in.initGame(level);
        if(gameParam != null) {
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
                    switch(gameParam.getBoard()[i][j]) {
                        case 1:
                            this.gameBoard[i][j] = new Wall();
                            break;
                        case 2:
                            this.gameBoard[i][j] = new PacDot(gameParam.getPacDotValue());
                            break;
                        case 3:
                            this.gameBoard[i][j] = new Fruit(gameParam.getLevel(), gameParam.getFruitValue());
                            break;
                        case 4 :
                            this.gameBoard[i][j] = new SuperPacDot();
                            break;
                        case 5 :
                            Ghost g = new Ghost(gameParam.getGameSpeed(), gameParam.getStartGhostX(), gameParam.getStartGhostY(), i, j);
                            this.gameGhostBoard[i][j] = g;
                            this.ghostList.add(g);
                            Ghost.countGhost++;
                            break;
                        case 6 :
                            this.pacMan = new PacMan(gameParam.getGameSpeed(), i, j);
                    }
                }
            }
        }
    }

    /**
     * Jeu du PacMan, choix du déplacement (dx, dy)
     * @param mouvement paramètre tempraire
     */
    public synchronized void play(int mouvement) {
        int dx = 0;
        int dy = 0;
        switch (mouvement) {
            case 1: // Droite
                dx = 0;
                dy = 1;
                break;
            case 2: // haut
                dx = -1;
                dy = 0;
                break;
            case 3: // Bas
                dx = 1;
                dy = 0;
                break;
            case 4: // Gauche
                dx = 0;
                dy = -1;
                break;
            default:
                break;
        }

        if (this.isValidBoardMove(this.pacMan.getX(), this.pacMan.getY(), dx, dy)) {
            movePacMan(dx, dy);
            this.previousDX = dx;
            this.previousDY = dy;
        } else {
            if (this.isValidBoardMove(this.getPacMan().getX(), this.getPacMan().getY(), this.previousDX, this.previousDY)) {
                movePacMan(this.previousDX, this.previousDY);
            }
        }
    }

    /**
     * Jeu des fantomes, choix du déplacement (dx, dy)
     * @param g le fantôme concerné
     * @param dx paramètre temporaire
     * @param dy paramètre temporaire
     * @pre g != null
     */
    public void play(Ghost g, int dx, int dy){
        // int dx = 0;
        // int dy = 0;

        if (g.isStateEaten()) {
        } else if (g.getName().equals(GhostNames.Oikake.toString())) {
            //TODO deplacement premier ghost
        } else if (g.getName().equals(GhostNames.Machibuse.toString())) {
            //TODO deplacement deuxieme ghost
        } else if (g.getName().equals(GhostNames.Kimagure.toString())) {
            //TODO deplacement troisieme ghost
        } else if (g.getName().equals(GhostNames.Otoboke.toString())) {
            //TODO deplacement quatrieme ghost
        }

        if (this.isValidBoardMove(g.getX(), g.getY(), dx, dy)) {
            moveGhost(g, dx, dy);
        }
    }

    /**
     * Vérifie qu'un mouvement n'est que d'une case et pas en diagonale
     * @param dx déplacement sur les x
     * @param dy déplacement sur les y
     * @return vrai si le déplacement est valide, false dans le cas contraire
     */
    private boolean isValidMove(int dx, int dy){
        boolean ret = false;
        if ((Math.abs(dx) == 1 && dy == 0) || (dx == 0 && Math.abs(dy) == 1)) {
            ret = true;
        }
        return ret;
    }

    /**
     * Vérifie si le mouvement est valide sur le plateau (pas dans un mur, etc)
     * @param x position en x de l'élément à déplacer
     * @param y position en y de l'élément à déplacer
     * @param dx déplacement en x à effectuer
     * @param dy déplacement en y à effectuer
     * @return vrai si le déplacement est valide, false dans le cas contraire
     */
    private boolean isValidBoardMove(int x, int y, int dx, int dy) {
        boolean result = true;
        if (this.isValidMove(dx, dy)) {
            if (y == 0 && dy == -1 || y == this.gameBoard[0].length-1 && dy == 1) {
                result = true;
            } else if (x == 0 && dx == -1 || x == this.gameBoard.length-1 && dx == 1) {
                result = true;
            } else if (x+dx < 0 || x+dx > gameBoard[0].length || y+dy < 0 || y+dy > gameBoard.length) {
                result = false;
            } else if (this.gameBoard[x+dx][y+dy] instanceof Wall) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Déplacement du PacMan
     * @param dx déplacement en x de PacMan
     * @param dy déplacement en y de PacMan
     */
    private void movePacMan(int dx, int dy) {

        int x = this.pacMan.getX();
        int y = this.pacMan.getY();

        if (y == 0 && dy == -1) {
            dy = this.gameBoard[0].length-1;
        } else if (y == this.gameBoard[0].length-1 && dy == 1) {
            dy = -(this.gameBoard[0].length-1);
        } else if (x == 0 && dx == -1) {
            dx = this.gameBoard.length-1;
        } else if (x == this.gameBoard.length-1 && dx == 1) {
            dx = -(this.gameBoard.length-1);
        }

        if (this.gameGhostBoard[x + dx][y + dy] != null && !this.gameGhostBoard[x + dx][y + dy].isStateEaten()) {
            if (this.power) {
                killGhost(x+dx, y+dy);
                this.pacMan.setX(x+dx);
                this.pacMan.setY(y+dy);
            } else {
                killPacMan();
            }
        } else {
            if (this.gameBoard[x + dx][y + dy] instanceof SuperPacDot) {
                this.power = true;
                this.comboCount = 1;
                //this.erase(x+dx, y+dx);
                //TODO gérer le temps du power

            } else if (this.gameBoard[x + dx][y + dy] instanceof Fruit) {
                this.score += Fruit.value;
                this.winLife();
                //this.erase(x+dx, y+dx);
            } else if (this.gameBoard[x + dx][y + dy] instanceof PacDot) {
                this.score += PacDot.value;
                this.winLife();
                //this.erase(x+dx, y+dy);
            }
            this.pacMan.setX(x+dx);
            this.pacMan.setY(y+dy);
        }
    }

    /**
     * Déplace un fantôme
     * @param ghost fantôme à déplacer
     * @param dx déplacement en x à effectuer
     * @param dy déplacement en y à effectuer
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
            dy = this.gameBoard[0].length-1;
        } else if (y == this.gameBoard[0].length-1 && dy == 1) {
            dy = -(this.gameBoard[0].length-1);
        } else if (x == 0 && dx == -1) {
            dx = this.gameBoard.length-1;
        } else if (x == this.gameBoard.length-1 && dx == 1) {
            dx = -(this.gameBoard.length-1);
        }

        ghost.setX(x+dx);
        ghost.setY(y+dy);
        if (this.gameGhostBoard[x+dx][y+dy] == null) {
            this.gameGhostBoard[x+dx][y+dy] = ghost;
            this.gameGhostBoard[x][y] = null;
        } else {
            this.gameGhostBoard[x][y] = null;
        }
    }

    /**
     * Evenement lorsque PacMan mange un fantôme
     * @param x position du fantôme mangé
     * @param y position du fantôme mangé
     */
    public void killGhost (int x, int y) {
        this.score += Ghost.getValue()*comboCount;
        this.winLife();
        this.comboCount += 1;
        this.gameGhostBoard[x][y].setStateEaten(true);
        this.restartGhost(gameGhostBoard[x][y]);
    }

    /**
     * Evenement lorsque PacMan est mangé
     */
    public void killPacMan() {
        this.life -= 1;
        if ( this.life < 0) {
            end();
        }
        this.pacMan.setX(this.pacMan.getStartX());
        this.pacMan.setY(this.pacMan.getStartY());
    }

    private void restartGhost(Ghost ghost) {

    }

    /**
     * Augmente le nombre de vie à chaque tranche de 10k points
     */
    private void winLife() {
        if (this.score >= 10000) {
            this.life += 1;
            this.finalScore += score;
            this.score = 0;
        }
    }

    /**
     * Retourne la piece à la position (x,y) donnée (hors PacMan)
     * @param x position en x
     * @param y position en y
     * @return la pièce en (x,y)
     */
    protected GamePiece getPiece(int x, int y) {
        GamePiece result =  null;

        if (this.gameGhostBoard[x][y] != null) {
            result = this.gameGhostBoard[x][y];
        } else {
            result = this.gameBoard[x][y];
        }
        return result;
    }

    /**
     * Efface un élément à la position x, y donnée
     * @param x position en x
     * @param y position en y
     */
    protected void erase(int x, int y) {
        this.gameBoard[x][y] = null;
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
                    System.out.print(gh.getName()+"  ");
                } else {
                    if (g != null) {
                        System.out.print(g.getName()+"  ");
                    } else {
                        System.out.print("00"+"  ");
                    }
                }
            }
            System.out.println("");
        }
        this.gameBoard[this.pacMan.getX()][this.pacMan.getY()] = null;
    }

    /**
     * Fin
     */
    private void end() {
        //TODO end
    }

    /**
     * Retoune le nombre de point de vie restant
     * @return un entier correspondant au point de vie restant à PacMan
     * @post result = this.life
     */
    public int getLife() {
        return this.life;
    }

    /**
     * Modifier le nombre de point de vie
     * @param life un entier positif remplacant le nombre de point de vie de PacMan
     * @pre {@code life >= 0}
     * @post this.life = life
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * Retourne true si PacMan a un pouvoir, false sinon
     * @return un booléen indiquant si PacMan possede un pouvoir
     * @post result = power
     */
    public boolean isPower() {
        return this.power;
    }

    /**
     * Modifie l'état du pouvoir de PacMan
     * @param power un booléen indiquant si PacMan possède un pouvoir
     * @post this.power = power
     */
    public void setPower(boolean power) {
        this.power = power;
    }

    /**
     * Retourne le score
     * @return une entier représentant le score
     * @post result = score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Modifie la valeur du score
     * @param score un entier
     * @pre {@code score >= 0}
     * @post this.score = score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Retourne le score finale
     * @return le score finale
     * @post result = finalScore
     */
    public int getFinalScore() {
        return finalScore;
    }

    /**
     * Modifie le score finale
     * @param finalScore new finalScore
     * @pre this.finalScore = finalScore
     */
    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public int getComboCount() {
        return comboCount;
    }

    public void setComboCount(int comboCount) {
        this.comboCount = comboCount;
    }

    public GamePiece[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GamePiece[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Ghost[][] getGameGhostBoard() {
        return gameGhostBoard;
    }

    public void setGameGhostBoard(Ghost[][] gameGhostBoard) {
        this.gameGhostBoard = gameGhostBoard;
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public ArrayList<Ghost> getGhostList() {
        return ghostList;
    }

    public void setGhostList(ArrayList<Ghost> ghostList) {
        this.ghostList = ghostList;
    }

    public int getPreviousDX() {
        return previousDX;
    }

    public void setPreviousDX(int previousDX) {
        this.previousDX = previousDX;
    }

    public int getPreviousDY() {
        return previousDY;
    }

    public void setPreviousDY(int previousDY) {
        this.previousDY = previousDY;
    }

    public int getPowerDuration() {
        return powerDuration;
    }

    public void setPowerDuration(int powerDuration) {
        this.powerDuration = powerDuration;
    }
}

