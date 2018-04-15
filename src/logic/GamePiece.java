package logic;

/**
 * Cette classe modélise les pièces du jeu PacMan.
 *
 * @author Théophile Chénais
 */
public abstract class GamePiece {

    /**
     * Nom de la pièce
     */
    public String name;

    /**
     * Retourne le nom de la pièce
     *
     * @return name
     * @post result = name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Modifie le nom de la pièce
     *
     * @param name new name
     * @post this.name = name
     */
    public void setName(String name) {
        this.name = name;
    }
}
