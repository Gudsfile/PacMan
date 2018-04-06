package logic;

/**
 *
 * Cette classe modélise les super-gommes du jeu PacMan.
 * @author Théophile Chénais
 *
 * @inv {@code powerDuration > 0}
 */
public class SuperPacDot extends PacDot {

    /**
     * Durée du pouvoir
     */
    private int powerDuration;

    /**
     * Construit un SuperPacDot
     * @param powerDuration durée du pouvoir
     * @pre {@code powerDuration > 0}
     * @post this.powerDuration = powerDuration
     */
    SuperPacDot(int powerDuration) {
        super(0);
        this.powerDuration = powerDuration;
    }

    /**
     * get powerDuration
     * @return powerDuration
     * @post result = powerDuration
     */
    public int getPowerDuration() {
        return powerDuration;
    }

    /**
     * set powerDuration
     * @param powerDuration new powerDuration
     * @pre {@code powerDuration > 0}
     * @post this.powerDuration = powerDuration
     */
    public void setPowerDuration(int powerDuration) {
        this.powerDuration = powerDuration;
    }
}
