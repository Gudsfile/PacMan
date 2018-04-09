package logic;

/**
 *
 * Cette classe modélise les fantôme du jeu PacMan.
 * @author Théophile Chénais
 *
 * @inv {@code speed >= 0}
 */
public class Ghost extends GamePiece {

    protected static int countGhost = 0;

    /**
     * Vitesse du fantôme
     */
    private int speed;
    /**
     * Valeur du fantôme
     */
    private static int value = 200;
    /**
     * Position de départ
     */
    private static int startX;
    private static int startY;
    /**
     * Coordonée x du fantôme
     */
    private int x;
    /**
     * Coordonée y du fantôme
     */
    private int y;
    private boolean stateEaten;

    /**
     * Construit un fantôme
     * @pre name != null
     * @pre speed >= 0
     * @post this.name = name
     * @post this.speed = speed
     */
    Ghost(int speed, int startX, int startY, int x, int y) {
        super();
        this.speed = speed;
        Ghost.startX = startX;
        Ghost.startY = startY;
        this.x = x;
        this.y = y;
        switch (countGhost) {
            case 0:
                this.name = GhostNames.Oikake.toString();
                break;
            case 1 :
                this.name = GhostNames.Machibuse.toString();
                break;
            case 2 :
                this.name = GhostNames.Kimagure.toString();
                break;
            case 3 :
                this.name = GhostNames.Otoboke.toString();
                break;
        }
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

    /**
     *
     * @return
     */
    public static int getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(int value) {
        Ghost.value = value;
    }

    public static int getStartX() {
        return startX;
    }

    public static void setStartX(int startX) {
        Ghost.startX = startX;
    }

    public static int getStartY() {
        return startY;
    }

    public static void setStartY(int startY) {
        Ghost.startY = startY;
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
