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
    PacMan(int speed) {
        super();
        this.speed = speed;
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
