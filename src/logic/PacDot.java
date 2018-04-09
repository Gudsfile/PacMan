package logic;

/**
 *
 * Cette classe modélise les PacDot du jeu PacMan.
 * @author Théophile Chénais
 *
 * @inv {@code value > 0}
 *
 */
public class PacDot extends GamePiece {

    /**
     * Valeur du PacDot
     */
    private static int value;

    /**
     * Construit un PacDot
     * @param value valeur de la gomme
     * @pre {@code value > 0}
     * @post this.value = value
     */
    PacDot(int value) {
        super();
        PacDot.value = value;
    }

    /**
     * get value
     * @return value
     * @post result = value
     */
    public int getValue() {
        return value;
    }

    /**
     * set value
     * @param value new value
     * @pre {@code value > 0}
     * @post this.value = value
     */
    public void setValue(int value) {
        this.value = value;
    }
}
