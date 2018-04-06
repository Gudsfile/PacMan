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
}
