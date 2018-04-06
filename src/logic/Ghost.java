package logic;

/**
 *
 * Cette classe modélise les fantôme du jeu PacMan.
 * @author Théophile Chénais
 *
 * @inv {@code speed >= 0}
 */
public class Ghost extends GamePiece {

    /**
     * Vitesse du fantôme
     */
    private int speed;
    private String name;

    /**
     * Construit un fantôme
     * @pre name != null
     * @pre speed >= 0
     * @post this.name = name
     * @post this.speed = speed
     */
    Ghost(int speed, String name) {
        super();
        this.speed = speed;
        this.name = name;
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
     * @pre speed >= 0
     * @post this.speed = speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * get name
     * @return name
     * @post result = name
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     * @param name new name
     * @post this.name = name
     */
    public void setName(String name) {
        this.name = name;
    }
}
