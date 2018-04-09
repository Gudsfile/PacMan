package logic;

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
     * Construit une partie
     * @param life entier, nombre de vie que PacMan possède
     * @param gameParam objet GameParam, contient tous les paramètres de la partie
     * @pre  {@code gameParam != null}
     * @post this.life = life
     * @post this.score = 0
     */
    public Game(int life, GameParam gameParam) {
        if (gameParam != null) {
            this.life = life;
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



}

