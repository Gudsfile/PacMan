package logic;

import java.awt.*;

/**
 *
 * @inv {@code values > 0}
 *
 */
public abstract class PacDot extends GamePiece {

    /**
     * Valeur du PacDot
     */
    private int values;

    public PacDot(String name, Color color, int values) {
        super(name, color);
        this.values = values;
    }
}
