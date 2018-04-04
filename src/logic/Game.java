package logic;

/**
 *
 *
 *
 * @inv this.life >=0
 * @inv this.gameBoard != null
 * @inv this.gameBoard.width > 0
 * @inv this.gameBoard.height > 0
 *
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
     * Tableau de Ghost d'une dimension correspondant à la liste des fantômes de la partie
     */
    private Ghost[] ghostList;
    /**
     * Tableau de PacDot d'une dimension correspondant à la liste des gommes de la partie
     */
    private PacDot[] pacDotList;
    /**
     * Objet PacMan représentant le PacMan de la partie
     */
    private PacMan pacMan;
    /**
     * Objet GameBoard correspondant au plateau de la partie
     */
    private GameBoard gameBoard;

    /**
     * Construit une partie
     * @param life entier, nombre de vie que PacMan possède
     * @param power booléen, pouvoir de PacMan
     * @pre life >= 0
     * @pre gameBoard != null
     * @pre gameBoard.width > 0
     * @pre gameBoard.height > 0
     * @pre nbRegularDots > 0
     * @pre nbPowerDots >= 0
     * @pre nbRegularDots + nbPowerDots < gameBoard.size[0]*gameBoard.size[1]
     * @post this.life = life
     * @post this.power = power
     * @post this.gameBoard = gameBoard
     * @post this.nbRegularDots = nbRegularDots
     * @post this.nbPowerDots = nbPowerDots
     */
    public Game(int life, boolean power, GameBoard gameBoard, int nbRegularDots, int nbPowerDots) {

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
     * @pre life >= 0
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
     * Retourne la liste des fantômes
     * @return tableau de Ghost
     * @post result = ghostList
     */
    public Ghost[] getGhostList() {
        return ghostList;
    }

    /**
     * Modifie la liste des fantômes
     * @param ghostList tableau de Ghost
     * @pre ghostList != null
     * @post this.ghostList = ghostList
     */
    public void setGhostList(Ghost[] ghostList) {
        this.ghostList = ghostList;
    }

    /**
     * Retourne la liste des PacDots
     * @return tableau de PacDot
     * @post result = pacDotList
     */
    public PacDot[] getPacDotList() {
        return pacDotList;
    }

    /**
     * Modifie la liste des PacDots
     * @param pacDotList tableau de PacDot
     * @pre pacDotList != null
     * @post this.pacDotList = pacDotList
     */
    public void setPacDotList(PacDot[] pacDotList) {
        this.pacDotList = pacDotList;
    }

    /**
     * Retourne le PacMan
     * @return objet PacMan
     * @post result = pacMan
     */
    public PacMan getPacMan() {
        return pacMan;
    }

    /**
     * Modifie le PacMan
     * @param pacMan objet PacMan
     * @pre pacMan != null
     * @post this.pacMan = pacMan
     */
    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
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
     * @pre gameBoard.width > 0
     * @pre gameBoard.height > 0
     * @post this.gameBoard = gameBoard
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }



}

