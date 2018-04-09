package logic;


/**
 *
 * Cette classe modélise PacMan du jeu PacMan.
 * @author Théophile Chénais
 *
 * @inv {@code speed > 0}
 *
 */
public class PacMan extends GamePiece {

    /**
     * Vitesse du PacMan
     */
    private int speed;
    /**
     * Position x de départ
     */
    private int startX;
    /**
     * Position y de départ
     */
    private int startY;
    /**
     * Coordonnée x du PacMan
     */
    private int x;
    /**
     * Coordonée y du PacMan
     */
    private int y;

    /**
     * Construit un PacMan
     * @pre {@code speed > 0}
     * @post this.speed = speed
     */
    PacMan(int speed, int startX, int startY, int x, int y) {
        super();
        this.name = "\033[34m" + "PC" + "\033[39m";
        this.speed = speed;
        this.startX = startX;
        this.startY = startY;
        this.x = x;
        this.y = y;
    }

    /**
     * Get speed
     * @return speed
     * @post result = speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * set speed
     * @param speed new speed
     * @pre {@code speed >= 0}
     * @post this.speed = speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    /**
     * get x
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * set x
     * @param x new x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * get y
     * @return set y
     */
    public int getY() {
        return y;
    }

    /**
     * set y
     * @param y set y
     */
    public void setY(int y) {
        this.y = y;
    }
}
