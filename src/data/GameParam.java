package data;

/**
 * Cette class permet de stocker les paramètre d'une partie de PacMan lu à partir d'un fichier JSON de paramètre
 *
 * @author Alexis Cano
 * @inv {@code this.level > 0}
 * @inv {@code this.pacDotValue > 0}
 * @inv {@code this.fruitValue > 0}
 * @inv {@code this.powerTime > 0}
 * @inv {@code this.gameSpeed > 0}
 * @inv {@code this.board != null}
 * @inv {@code this.ghostBoard != null}
 */
public class GameParam {

    /**
     * Le niveau de jeu
     */
    private int level;
    /**
     * Le nombre de point pour un PacDot
     */
    private int pacDotValue;
    /**
     * Le nombre de point pour un Fruit
     */
    private int fruitValue;
    /**
     * La durée en seconde du pouvoir
     */
    private int powerTime;
    /**
     * La vitesse du jeu
     */
    private int gameSpeed;
    /**
     * La position x de départ du PacMan
     */
    private int startPacManX;
    /**
     * La position y de départ du PacMan
     */
    private int startPacManY;
    /**
     * La position x de départ des fantomes
     */
    private int startGhostX;
    /**
     * La position y de départ des fantomes
     */
    private int startGhostY;

    /**
     * Le plateau de jeu contenant les différentes pièces (PacDot, Fruit, PacMan)
     */
    private int[][] board;

    /**
     * Constructeur, initialise les attribut de classe
     *
     * @param level       le niveau de jeu
     * @param pacDotValue le nombre de point pour un PacDot
     * @param fruitValue  le nombre de point pour un Fruit
     * @param powerTime   le temps en seconde du power
     * @param gameSpeed   la vitesse du jeu
     * @param dataBoard   le plateau de jeu (tableau d'entier, 0 = vide, 1 = wall, 2 = regularPacDot, 3 = fruit, 4 = SuperPacDot, 5 = ghost, 6 = pacMan
     * @pre {@code level > 0}
     * @pre {@code pacDotValue > 0}
     * @pre {@code fruitValue > 0}
     * @pre {@code powerTime > 0}
     * @pre {@code gameSpeed > 0}
     * @pre {@code dataBoard != null}
     * @post {@code this.level = level}
     * @post {@code this.pacDotValue = pacDotValue}
     * @post {@code this.fruitValue = fruitValue}
     * @post {@code this.powerTime = powerTime}
     * @post {@code this.gameSpeed = gameSpeed}
     */
    public GameParam(int level, int pacDotValue, int fruitValue, int powerTime, int gameSpeed, int[][] dataBoard, int startPacManX, int startPacManY, int startGhostX, int startGhostY) {
        if (level > 0 && pacDotValue > 0 && fruitValue > 0 && powerTime > 0 && gameSpeed > 0 && dataBoard != null) {
            this.level = level;
            this.pacDotValue = pacDotValue;
            this.fruitValue = fruitValue;
            this.powerTime = powerTime;
            this.gameSpeed = gameSpeed;
            this.board = dataBoard;
            this.startPacManX = startPacManX;
            this.startPacManY = startPacManY;
            this.startGhostX = startGhostX;
            this.startGhostY = startGhostY;
        }
    }

    public boolean invarriant() {
        if (this.level > 0 && this.pacDotValue > 0 && this.fruitValue > 0 && this.powerTime > 0 && this.gameSpeed > 0 && this.board != null) {
            return true;
        } else {
            return false;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Get pacDotValue
     *
     * @return pacDotValue
     */
    public int getPacDotValue() {
        return pacDotValue;
    }

    /**
     * Set pacDotValue
     *
     * @param pacDotValue new pacDotValue
     */
    public void setPacDotValue(int pacDotValue) {
        this.pacDotValue = pacDotValue;
    }

    /**
     * Get fruitValue
     *
     * @return fruitValue
     */
    public int getFruitValue() {
        return fruitValue;
    }

    /**
     * Set fruitValue
     *
     * @param fruitValue new fruitValue
     */
    public void setFruitValue(int fruitValue) {
        this.fruitValue = fruitValue;
    }

    /**
     * Get powerTime
     *
     * @return powerTime
     */
    public int getPowerTime() {
        return powerTime;
    }

    /**
     * Set powerTime
     *
     * @param powerTime new powerTime
     */
    public void setPowerTime(int powerTime) {
        this.powerTime = powerTime;
    }

    /**
     * Get gameSpeed
     *
     * @return gameSpeed
     */
    public int getGameSpeed() {
        return gameSpeed;
    }

    /**
     * Set gameSpeed
     *
     * @param gameSpeed new gameSpeed
     */
    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    /**
     * Get board
     *
     * @return board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Set board
     *
     * @param board new board
     */
    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getStartPacManX() {
        return startPacManX;
    }

    public void setStartPacManX(int startPacManX) {
        this.startPacManX = startPacManX;
    }

    public int getStartPacManY() {
        return startPacManY;
    }

    public void setStartPacManY(int startPacManY) {
        this.startPacManY = startPacManY;
    }

    public int getStartGhostX() {
        return startGhostX;
    }

    public void setStartGhostX(int startGhostX) {
        this.startGhostX = startGhostX;
    }

    public int getStartGhostY() {
        return startGhostY;
    }

    public void setStartGhostY(int startGhostY) {
        this.startGhostY = startGhostY;
    }
}
