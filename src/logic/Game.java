package logic;

import data.FileReader;
import data.GameParam;
import view.Play;

/**
 *
 * Cette classe modélise une partie du jeu PacMan.
 * @author Théophile Chénais
 * @inv {@code this.life >=0}
 * @inv {@code this.gameBoard != null}
 * @inv {@code this.gameBoard.width > 0}
 * @inv {@code this.gameBoard.height > 0}
 * @inv {@code this.score >= 0}
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
     * Objet GameBoard correspondant au plateau de la partie
     */
    private GameBoard gameBoard;
    /**
     * Entier contenant le score de la partie
     */
    private int score;
    /**
     * Compteur de combos fantômes mangés
     */
    private int comboCount;

    private int previousX = 0;
    private int previousY = 0;

    /**
     * Construit une partie
     * @param level niveau de la partie
     * @pre  level est un niveau qui existe
     * @post life = 3
     * @post score = 0
     * @post countGhost = 1
     */
    public Game(int level) {
        FileReader in = new FileReader("res/Levels/Level"+level+".json");
        Ghost.countGhost = 0;
        GameParam gameParam = in.initGame(level);
        if (gameParam != null) {
            this.life = 3;
            this.score = 0;
            this.comboCount = 1;
            this.power = false;
            this.gameBoard = new GameBoard(gameParam);
        }
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
     * Retourne le plateau de jeu
     * @return plateau GameBoard
     * @post result = gameBoard
     */
    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

    /**
     * Modifie le plateau de jeu
     * @param gameBoard plateau GameBoard
     * @pre gameBoard != null
     * @pre {@code gameBoard.width > 0}
     * @pre {@code gameBoard.height > 0}
     * @post this.gameBoard = gameBoard
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
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
     * Jeu du pacman (choix du dx, dy)
     */
    public void play(int mouvement) {
        //int mouvement = Play.getTouche();
        int dx = 0;
        int dy = 0;

        switch (mouvement) {
            case 1:
                dx = 0;
                dy = 1;
                break;
            case 2:
                dx = -1;
                dy = 0;
                break;
            case 3:
                dx = 1;
                dy = 0;
                break;
            case 4:
                dx = 0;
                dy = -1;
                break;
            default:
                break;
        }
        if (this.gameBoard.isValidMovePacMan(dx, dy)) {
            movePacMan(dx, dy);
            this.previousX = dx;
            this.previousY = dy;
        } else {
            if (this.gameBoard.isValidMovePacMan(this.previousX, this.previousY)) {
                movePacMan(this.previousX, this.previousY);
            }
        }
    }

    /**
     * Jeu d'un fantôme (choix du dx, dy)
     */
    public void play(Ghost g, int dx, int dy){

        if (g.isStateEaten()) {
            //TODO aller vers g.getStartX && g.getStartY
        } else if (g.getName().equals(GhostNames.Oikake.toString())) {
            //TODO deplacement premier ghost
        } else if (g.getName().equals(GhostNames.Machibuse.toString())) {
            //TODO deplacement deuxieme ghost
        } else if (g.getName().equals(GhostNames.Kimagure.toString())) {
            //TODO deplacement troisieme ghost
        } else if (g.getName().equals(GhostNames.Otoboke.toString())) {
            //TODO deplacement quatrieme ghost
        }

        moveGhost(g, dx, dy);

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

        if (this.gameBoard.isValidMove(x, y, dx, dy)) {
            if (this.gameBoard.getPacMan().getX() == x+dx && this.gameBoard.getPacMan().getY() == y+dy && !this.power) {
                PacManEaten();
            } else if (this.gameBoard.getPacMan().getX() == x+dx && this.gameBoard.getPacMan().getY() == y+dy && this.power) {
                PacManEatsGhost(x+dx, y+dy);
            }

            ghost.setX(x+dx);
            ghost.setY(y+dy);
            this.gameBoard.getGameGhostBoard()[x+dx][y+dy] = ghost;
            this.gameBoard.getGameGhostBoard()[x][y] = null;
        }

    }

    /**
     * Déplacement du PacMan
     * @param dx déplacement en x de PacMan
     * @param dy déplacement en y de PacMan
     */
    private void movePacMan(int dx, int dy) {

        int x = this.gameBoard.getPacMan().getX();
        int y = this.gameBoard.getPacMan().getY();

        if (this.gameBoard.getGameGhostBoard()[x + dx][y + dy] != null) {
            if (!this.power) {
                PacManEaten();
            }
            if (this.power) {
                PacManEatsGhost(x+dx, y+dy);
            }
        } else if (this.gameBoard.getGamePieceBoard()[x + dx][y + dy] instanceof SuperPacDot) {
            this.power = true;
            this.comboCount = 1;
            //TODO gérer le temps
        } else if (this.gameBoard.getGamePieceBoard()[x + dx][y + dy] instanceof Fruit) {
            this.score += Fruit.value;
            //TODO gagner vie ?
        } else if (gameBoard.getGamePieceBoard()[x + dx][y + dy] instanceof PacDot) {
            this.score += PacDot.value;
            //TODO gagner vie ?
        }
        this.gameBoard.getPacMan().setX(x+dx);
        this.gameBoard.getPacMan().setY(y+dy);
    }

    /**
     * Evenement lorsque PacMan est mangé
     */
    private void PacManEaten () {
        this.life -= 1;
        if ( this.life < 0) {
            end();
        }
        this.gameBoard.getPacMan().setX(this.gameBoard.getPacMan().getStartX());
        this.gameBoard.getPacMan().setY(this.gameBoard.getPacMan().getStartY());
    }

    /**
     * Evenement lorsque PacMan mange un fantôme
     * @param x position du fantôme mangé
     * @param y position du fantôme mangé
     */
    private void PacManEatsGhost (int x, int y) {
        this.score += Ghost.getValue()*comboCount;
        //TODO gagner vie ?
        this.comboCount += 1;
        this.gameBoard.getGhost(x,y).setStateEaten(true);
        //TODO diriger le fantôme vers startGhostX et startGhostY
    }


    /**
     * Fin
     */
    private void end() {
        //TODO end
    }


}

