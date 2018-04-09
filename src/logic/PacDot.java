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
    protected static int value;

    /**
     * Construit un PacDot
     * @param value valeur de la gomme
     * @pre {@code value > 0}
     * @post this.value = value
     */
    PacDot(int value) {
        super();
        this.name = "\033[35m" + "--" + "\033[39m";
        PacDot.value = value;
    }
}
