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
     * Position de départ en x
     */
    private static int startX;
    /**
     * Position de départ en y
     */
    private static int startY;
    /**
     * Coordonée x du fantôme
     */
    private int x;
    /**
     * Coordonée y du fantôme
     */
    private int y;
    /**
     * Etat du fantôme (mangé ou non)
     */
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
                this.name = "\033[31m" + "G1" + "\033[39m";//GhostNames.Oikake.toString();
                break;
            case 1 :
                this.name = "\033[32m" + "G2" + "\033[39m";//GhostNames.Machibuse.toString();
                break;
            case 2 :
                this.name = "\033[36m" + "G3" + "\033[39m";//GhostNames.Kimagure.toString();
                break;
            case 3 :
                this.name = "\033[35m" + "G4" + "\033[39m";//GhostNames.Otoboke.toString();
                break;
        }
    }

    public boolean isValidMove(int dx, int dy){
        boolean ret = false;
        if ((Math.abs(dx) == 1 && dy == 0) || (dx == 0 && Math.abs(dy) == 1)) {
            ret = true;
        }
        return ret;
    }

    /**
     * Get speed
     * @return speed
     * @post result = speed
     */
    public int getSpeed() {
        return this.speed;
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
        return this.name;
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
     * get value
     * @return value
     * @post result = value
     */
    public static int getValue() {
        return value;
    }

    /**
     * set value
     * @param value new value
     * @post this.value = value
     */
    public void setValue(int value) {
        Ghost.value = value;
    }

    /**
     * Retourne la position de départ du fantôme
     * @return startX
     * @post result = startX
     */
    public static int getStartX() {
        return Ghost.startX;
    }

    /**
     * set startX
     * @param startX new startX
     * @post Ghost.startX = startX
     */
    public static void setStartX(int startX) {
        Ghost.startX = startX;
    }

    /**
     * get startY
     * @return startY
     * @post result = startY
     */
    public static int getStartY() {
        return Ghost.startY;
    }

    /**
     * set startY
     * @param startY new startY
     * @post Ghost.startY = startY
     */
    public static void setStartY(int startY) {
        Ghost.startY = startY;
    }

    /**
     * get x
     * @return x
     */
    public int getX() {
        return this.x;
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
        return this.y;
    }

    /**
     * set y
     * @param y set y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * get stateEaten
     * @return stateEaten
     * @post result = stateEaten
     */
    public boolean isStateEaten() {
        return this.stateEaten;
    }

    /**
     * set stateEaten
     * @param stateEaten new stateEaten
     * @post this.stateEaten = stateEaten
     */
    public void setStateEaten(boolean stateEaten) {
        this.stateEaten = stateEaten;
    }
}
