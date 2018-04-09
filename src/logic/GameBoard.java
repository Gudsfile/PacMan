package logic;

import data.*;

import java.util.ArrayList;

/**
 *
 * Cette classe modélise le plateau de jeu de PacMan.
 * @author Théophile Chénais
 *
 * @inv gamePieceBoard.lenght = gameGhostBoard.lenght
 */
public class GameBoard {

    /**
     * Tableau de Piece de deux dimensions indiquant la position des dots, des murs et du pacman
     */
    private GamePiece[][] gamePieceBoard;
    private Ghost[][] gameGhostBoard;
    private ArrayList<Ghost> ghostList = new ArrayList<>();
    private PacMan pacMan;

    /**
     * Construit un plateau de jeu
     * @param gameParam paramètres de la partie
     * @pre gameParam != null
     * @post gamePieceBoard et gameGhostBoard correspondent au gameParam
     */
    GameBoard(GameParam gameParam) {
        if(gameParam != null) {
            gamePieceBoard = new GamePiece[gameParam.getBoard().length][gameParam.getBoard()[0].length];
            gameGhostBoard = new Ghost[gameParam.getBoard().length][gameParam.getBoard()[0].length];

            for (int i = 0; i < gameParam.getBoard().length; i++) {
                for (int j = 0; j < gameParam.getBoard()[0].length; j++) {
                    switch(gameParam.getBoard()[i][j]) {
                        case 1:
                            gamePieceBoard[i][j] = new Wall();
                            break;
                        case 2:
                            gamePieceBoard[i][j] = new PacDot(gameParam.getPacDotValue());
                            break;
                        case 3:
                            gamePieceBoard[i][j] = new Fruit(gameParam.getLevel(), gameParam.getFruitValue());
                            break;
                        case 4 :
                            gamePieceBoard[i][j] = new SuperPacDot(gameParam.getPowerTime());
                            break;
                        case 5 :
                            Ghost g = new Ghost(gameParam.getGameSpeed(), gameParam.getStartGhostX(), gameParam.getStartGhostY(), j, i);
                            ghostList.add(g);
                            gameGhostBoard[i][j] = g;
                            break;
                        case 6 :
                            this.pacMan = new PacMan(gameParam.getGameSpeed(), gameParam.getStartGhostX(), gameParam.getStartGhostY(), j, i);
                    }
                }
            }
        }
    }

    /**
     * Retourne la largeur du plateau
     * @return un entier correspondant à la largeur du plateau
     * @post result = gamePieceBoard.width
     */
    public int getWidth () {
        return 0;
    }

    /**
     * Retourne la hauteur du plateau
     * @return un entier correspondant à la hauteur du plateau
     * @post result = gamePieceBoard.height
     */
    public int getHeight () {
        return 0;
    }

    /**
     * Retourne le tableau
     * @return le plateau de piece
     * @post result = gamePieceBoard
     */
    public GamePiece[][] getGamePieceBoard() {
        return gamePieceBoard;
    }

    /**
     * Modifie le plateau de jeu
     * @param gamePieceBoard tableau GamePiece à deux dimensions
     * @pre gamePieceBoard != null
     * @post this.gamePieceBoard = gamePieceBoard
     */
    public void setGamePieceBoard(GamePiece[][] gamePieceBoard) {
        this.gamePieceBoard = gamePieceBoard;
    }

    /**
     * Retourne le tableau
     * @return le plateau de fantôme
     * @post result = gameGhostBoard
     */
    public Ghost[][] getGameGhostBoard() {
        return gameGhostBoard;
    }

    /**
     * Modifie le plateau de jeu
     * @param gameGhostBoard tableau Ghost à deux dimensions
     * @pre gameGhostBoard != null
     * @post this.gameGhostBoard = gameGhostBoard
     */
    public void setGameGhostBoard(Ghost[][] gameGhostBoard) {
        this.gameGhostBoard = gameGhostBoard;
    }

    /**
     * Retourne le PacMan de la partie
     * @return pacMan
     * @post result = pacMan
     */
    protected PacMan getPacMan() {
        return pacMan;
    }

    protected boolean isValidMove(int x, int y, int dx, int dy) {
        boolean result = true;

        if (x < 0 || x > gamePieceBoard.length) {
            result = false;
        } else if (y < 0 || y > gamePieceBoard[0].length) {
            result = false;
        } else if (!(getPiece(x,y) instanceof Ghost)){
            result = false;
        } else if (!((dx == 1 && dy == 0) || (dx == 0 && dy == 1))) {
            result = false;
        } else if (x+dx < 0 || x+dx > gamePieceBoard[0].length) { //largeur
            result = false;
        } else if (y+dy < 0 || y+dy > gamePieceBoard.length) { //hauteur
            result = false;
        } else if (gamePieceBoard[x+dx][y+dy] instanceof Wall) {
            result = false;
        }

        return result;
    }

    protected boolean isValidMovePacMan(int dx, int dy) {
        boolean result = true;

        if (!((dx == 1 && dy == 0) || (dx == 0 && dy == 1))) {
            result = false;
        } else if (pacMan.getX()+dx < 0 || pacMan.getX()+dx > gamePieceBoard[0].length) { //largeur
            result = false;
        } else if (pacMan.getY()+dy < 0 || pacMan.getY()+dy > gamePieceBoard.length) { //hauteur
            result = false;
        } else if (gamePieceBoard[pacMan.getX()+dx][pacMan.getY()+dy] instanceof Wall) {
            result = false;
        }

        return result;
    }

    protected GamePiece getPiece(int x, int y) {
        GamePiece result =  null;

        if (this.gameGhostBoard[x][y] != null) {
            result = this.gameGhostBoard[x][y];
        } else {
            result = this.gamePieceBoard[x][y];
        }
        return result;
    }

}
