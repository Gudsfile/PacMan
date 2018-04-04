package logic;

/**
 *
 *
 * @inv width > 0
 * @inv height > 0
 *
 *
 */
public class GameBoard {

    /**
     * Entier correspondant à la largeur du plateau
     */
    private int width;
    /**
     * Entier correspondant à la hauteur du plateau
     */
    private int height;
    /**
     * Tableau de Piece de deux dimensions indiquant la position des dots, des murs et du pacman
     */
    private Piece[][] board;
    /**
     * Tableau de Ghost de deux dimensions indiquant la position des ghost
     */
    private Ghost[][] ghost;

    /**
     *
     * @param width
     * @param height
     * @param board
     * @param ghost
     */
    public GameBoard(int width, int height, Piece[][] board, Ghost[][] ghost) {
        this.width = width;
        this.height = height;
        this.board = board;
        this.ghost = ghost;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return
     */
    public Piece[][] getBoard() {
        return board;
    }

    /**
     *
     * @param board
     */
    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    /**
     *
     * @return
     */
    public Ghost[][] getGhost() {
        return ghost;
    }

    /**
     *
     * @param ghost
     */
    public void setGhost(Ghost[][] ghost) {
        this.ghost = ghost;
    }
}
