package logic;

/**
 * Cette classe modélise les murs du jeu PacMan.
 *
 * @author Théophile Chénais
 */
public class Wall extends GamePiece {

    /**
     * Construit un mur (Wall)
     */
    public Wall() {
        super();
        this.name = "\033[36m" + "[]" + "\033[39m";
    }

}
