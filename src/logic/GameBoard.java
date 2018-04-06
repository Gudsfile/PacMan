package logic;

import data.Entity;

/**
 *
 *
 * @inv pieceBoard.lenght = ghostBoard.lenght
 *
 */
public class GameBoard {

    /**
     * Tableau de Piece de deux dimensions indiquant la position des dots, des murs et du pacman
     */
    private GamePiece[][] gamePieceBoard;
    private Ghost[][] gameGhostBoard;

    /**
     * Construit un plateau de jeu
     * @param gamePieceBoard position des pieces hors fantôme
     * @pre {@code pieceBoard.lenght > 0}
     * @pre {@code ghostBoard.lenght > 0}
     * @post this.pieceBoard = pieceBoard
     * @post this.ghostBoard = ghostBoard
     */
    public GameBoard(GamePiece[][] gamePieceBoard) {

    }

    /**
     * Retourne la largeur du plateau
     * @return un entier correspondant à la largeur du plateau
     * @post result = pieceBoard.width
     */
    public int getWidth () {
        return 0;
    }

    /**
     * Retourne la hauteur du plateau
     * @return un entier correspondant à la hauteur du plateau
     * @post result = pieceBoard.height
     */
    public int getHeight () {
        return 0;
    }

    /**
     * Retourne le tableau
     * @return
     */
    public GamePiece[][] getGamePieceBoard() {
        return gamePieceBoard;
    }

    /**
     *
     * @param gamePieceBoard
     */
    public void setGamePieceBoard(GamePiece[][] gamePieceBoard) {
        this.gamePieceBoard = gamePieceBoard;
    }

    /**
     * Deplace un fantôme sur le plateau
     * @param dx déplacement en x de la piece
     * @param dy déplacement en y de la piece
     * @param x position en x de la piece
     * @param y position en y de la piece
     * @pre
     */
    void move(int dx, int dy, int x, int y) {
        if (getPiece(x, y) instanceof Ghost) {
            moveGhost(dx, dy, x, y);
        } else if (getPiece(x, y) instanceof PacMan) {
            movePacMan(dx, dy, x, y);
        }

    }

    void moveGhost() {

    }
    void movePacMan() {

    }

    void erase() {

    }


    /**
     * Retourne la pièce aux coordonnées données
     * @param x coordonnées en x
     * @param y coordonnées en y
     * @return
     */
    GamePiece getPiece(int x, int y) {
        GamePiece result =  null;

        if (this.gamePieceBoard[x][y] == null || this.gamePieceBoard[x][y] instanceof PacDot){
            result = this.gameGhostBoard[x][y];
        }
        else {
            result = this.gamePieceBoard[x][y];
        }
        return result;
    }

}
