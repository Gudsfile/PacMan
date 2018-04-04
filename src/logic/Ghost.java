package logic;

/**
 * @inv {@code posX >= 0}
 * @inv {@code posY >= 0}
 */
public class Ghost extends Piece {

    /**
     * Coordonnée x de la position du fantôme
     */
    private int posX;
    /**
     * Coordonnée y de la position du fantôme
     */
    private int posY;


    /**
     * Construit un fantôme
     * @param posX coordonnée x de la position du fantôme
     * @param posY coordonnée y de la position du fantôme
     * @pre  {@code posX >= 0}
     * @pre  {@code posX < GameBoard.size}
     * @pre  {@code posY >= 0}
     * @pre  {@code posY < GameBoard.size}
     * @post this.posX = posX
     * @post this.posY = posY
     */
    public Ghost(int posX, int posY) {

    }

    /**
     * Retourne la coordonnée x de Ghost
     * @return un entier correspondant à la coordonnée x de Ghost
     * @post result = posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Retourne la coordonnée y de Ghost
     * @return un entier correspondant à la coordonnée y de Ghost
     * @post result = posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Modifie la coordonnée x de la position de Ghost
     * @param posX entier représentant la coordonnée x de la position de Ghost
     * @pre  {@code posX >= 0}
     * @pre  {@code posX < GameBoard.size}
     * @post this.posX = posX
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Modifie la coordonnée y de la position de Ghost
     * @param posY entier représentant la coordonnée y de la position de Ghost
     * @pre  {@code posY >= 0}
     * @pre  {@code posY < GameBoard.size}
     * @post this.posY = posY
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
}
