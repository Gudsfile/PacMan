package logic;

/**
 *
 * Cette classe modélise les fruits du jeu PacMan.
 * @author Théophile Chénais
 *
 * @inv name != null
 */
public class Fruit extends PacDot {

    /**
     * Nom du fruit
     */
    private String name;
    private static int value;

    Fruit(int value, String name) {
        super(value);
        Fruit.value = value;
        this.name = name;
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
     * @pre name != null
     * @post this.name = name
     */
    public void setName(String name) {
        this.name = name;
    }
}
