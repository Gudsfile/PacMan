package logic;

/**
 * Cette classe modélise les super-gommes du jeu PacMan.
 */
public class SuperPacDot extends PacDot {

    /**
     * Construit un SuperPacDot
     */
    SuperPacDot() {
        super(0);
        this.name = "\033[35m" + "SP" + "\033[39m";
    }

}
