package logic;

import java.awt.*;

/**
 * @inv {@code speed >= 0}
 */
public class Ghost extends GamePiece {

    /**
     * Vitesse du fantôme
     */
    private int speed;

    /**
     * Construit un fantôme
     * @pre name != null
     * @pre color != null
     * @post this.name = name
     * @post this.color = color
     */
    public Ghost(String name, Color color) {
        super(name,color);
    }

}
