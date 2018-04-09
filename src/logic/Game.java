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
        return life;
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
        return power;
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
        return gameBoard;
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
        return score;
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


    public void play() {
        int mouvement = Play.getTouche();
         switch (mouvement) {
            case 1 :
                if (gameBoard.isValidMovePacMan(0,1)) {
                    movePacMan(0,1);
                }
                break;
            case 2 :
                if (gameBoard.isValidMovePacMan(-1,0)) {
                    movePacMan(-1,0);
                }
                break;
            case 3 :
                if (gameBoard.isValidMovePacMan(1,0)) {
                    movePacMan(1,0);
                }
                break;
            case 4 :
                if (gameBoard.isValidMovePacMan(0,-1)) {
                    movePacMan(0,-1);
                }
                break;
            default :
                break;
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

        if (gameBoard.isValidMove(x, y, dx, dy)) {
            if (gameBoard.getPacMan().getX() == x+dx && gameBoard.getPacMan().getY() == y+dy) {
                PacManEaten();
            }

            ghost.setX(x+dx);
            ghost.setY(y+dy);
            gameBoard.getGameGhostBoard()[x+dx][y+dy] = gameBoard.getGameGhostBoard()[x][y];
            gameBoard.getGameGhostBoard()[x][y] = null;
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
            this.gameBoard.erase(x+dx, y+dx);
            //TODO gérer le temps
        } else if (this.gameBoard.getGamePieceBoard()[x + dx][y + dy] instanceof Fruit) {
            this.score += Fruit.value;
            this.gameBoard.erase(x+dx, y+dx);
        } else if (gameBoard.getGamePieceBoard()[x + dx][y + dy] instanceof PacDot) {
            this.score += PacDot.value;
            this.gameBoard.erase(x+dx, y+dx);
        }
        this.gameBoard.getPacMan().setX(x+dx);
        this.gameBoard.getPacMan().setY(y+dy);
    }

    /**
     * Evenement lorsque PacMane est mangé
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
        this.comboCount += 1;
        //TODO diriger le fantôme vers startGhostX et startGhostY
    }

    /**
     * Fin
     */
    private void end() {
        //TODO end
    }


}

