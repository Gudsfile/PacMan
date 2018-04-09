package logic;

import data.FileReader;
import data.GameParam;

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
     * Position de départ du PacMan
     */
    private int startPacManX;
    private int startPacManY;
    /**
     * Position de départ du Ghost
     */
    private int startGhostX;
    private int startGhostY;
    /**
     * Compteur de combos fantôme
     */
    private int countGhost = 1;

    /**
     * Construit une partie
     * @param level niveau de la partie
     * @pre  level existe
     * @post this.life = 3
     * @post this.score = 0
     */
    public Game(int level) {
        FileReader in = new FileReader("res/Levels/Level"+level+".json");
        GameParam gameParam = in.initGame(level);
        if (gameParam != null) {
            this.life = 3;
            this.power = false;
            this.gameBoard = new GameBoard(gameParam);
            this.startPacManX = gameParam.getStartPacManX;
            this.startPacManY = gameParam.getStartPacManY;
            this.startGhostX = gameParam.getStartGhostX;
            this.startGhostY = gameParam.getStartGhostY;
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

    /**
     * Deplace un fantôme sur le plateau
     * @param dx déplacement en x de la piece
     * @param dy déplacement en y de la piece
     * @param x position en x de la piece
     * @param y position en y de la piece
     * @pre
     */
    public void move(int dx, int dy, int x, int y) {
        if (gameBoard.isValidMove(x, y, dx, dy)) {
            if (gameBoard.getPiece(x, y) instanceof Ghost) {
                moveGhost(dx, dy, x, y);
            } else if (gameBoard.getPiece(x, y) instanceof PacMan) {
                movePacMan(dx, dy, x, y);
            }
        }
    }

    /**
     * Déplace un fantôme
     * @param dx déplacement en x du fantôme
     * @param dy déplacement en y du fantôme
     * @param x position en x du fantôme
     * @param y position en y du fantôme
     * @pre piece instanceof Ghost
     * @pre isValidMove()
     * @post piece.x = x+dx and piece.y = y+dy
     */
    private void moveGhost(int dx, int dy, int x, int y) {

        if (gameBoard.gamePieceBoard[x+dx][y+dy] instanceof PacMan) {
            PacManEaten(x+dx, y+dy);
        }
        gameBoard.gameGhostBoard[x+dx][y+dy] = gameBoard.gameGhostBoard[x][y];
        gameBoard.gameGhostBoard[x][y] = null;

    }

    /**
     * Déplace un PacMan
     * @param dx déplacement en x du PacMan
     * @param dy déplacement en y du PacMan
     * @param x position en x du PacMan
     * @param y position en y du PacMan
     * @pre piece instanceof PacMan
     * @pre x+dx and y+dy in the board
     * @post piece.x = x+dx and piece.y = y+dy
     */

    private void movePacMan(int dx, int dy, int x, int y) {
        //TODO Augmenter le score si pacdot + power ?
        //TODO Cas où Fantôme ? Fantôme + Power ?

        if (gameBoard.gameGhostBoard[x + dx][y + dy] != null) {
            if (!this.power) { //PacMan mangé
                PacManEaten(x+dx, y+dy);
            }
            if (this.power) {
                PacManEatsGhost(x+dx, y+dy);
            }
        } else if (gameBoard.gamePieceBoard[x + dx][y + dy] instanceof Fruit) {

        }


        gameBoard.gamePieceBoard[x+dx][y+dy] = gameBoard.gamePieceBoard[x][y];
        gameBoard.gamePieceBoard[x][y] = null;
    }

    /**
     * Supprime une piece du plateau
     * @param x position de la piece en x
     * @param y position de la piece en y
     * @pre piece instanceof PacDot
     * @post gamePieceBoard[x][y] = null
     */
    private void erase(int x, int y) {
        gameBoard.gamePieceBoard[x][y] = null;
    }

    private void PacManEaten (int x, int y) {
        this.life -= 1;
        if ( this.life < 0) {
            end();
        }
        gameBoard.gamePieceBoard[x][y] = gameBoard.gamePieceBoard[startPacManX][startPacManY];
    }

    private void PacManEatsGhost (int x, int y) {
        this.score += Ghost.getValue()*countGhost;
        this.countGhost += 1;
        //TODO diriger le fantôme vers startGhostX et startGhostY
    }

    private void end() {
        //affiche le score
    }


}

