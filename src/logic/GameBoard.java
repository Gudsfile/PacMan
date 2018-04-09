package logic;

import data.*;

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
    protected GamePiece[][] gamePieceBoard;
    protected Ghost[][] gameGhostBoard;
    protected PacMan pacMan;

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
                    Entity e = gameParam.getBoard()[i][j];
                    if (e instanceof EntityWall) {
                        gamePieceBoard[i][j] = new Wall();
                    } else if (e instanceof EntityRegularPacDot) {
                        gamePieceBoard[i][j] = new RegularPacDot(gameParam.getPacDotValue());
                    } else if (e instanceof EntityFruit) {
                        gamePieceBoard[i][j] = new Fruit(gameParam.getFruitValue(), e.getName());
                    } else if (e instanceof EntitySuperPacDot) {
                        gamePieceBoard[i][j] = new SuperPacDot(gameParam.getPowerTime());
                    } else if (e instanceof EntityGhost) {
                        gameGhostBoard[i][j] = new Ghost(gameParam.getGameSpeed(), e.getName());
                    } else if (e instanceof EntityPacMan) {
                        this.pacMan = new PacMan(gameParam.getGameSpeed());
                        this.pacMan.setX(gameParam.getStartPacManX());
                        this.pacMan.setY(gameParam.getStartPacManY());
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
     * Vérifie la validité d'une position
     * @param x position en x
     * @param y position en y
     * @param dx déplacement en x de la piece
     * @param dy déplacement en y de la piece
     * @return un booléen indiquant la validité de la position donnée
     */
    protected boolean isValidMove(int x, int y, int dx, int dy) {
        boolean result = true;

        if (x < 0 || x > gamePieceBoard.length) {
            result = false;
        } else if (y < 0 || y > gamePieceBoard[0].length) {
            result = false;
        } else if (!(gamePieceBoard[x][y] instanceof Ghost || gamePieceBoard[x][y] instanceof PacMan)){
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

    /**
     * Retourne la pièce aux coordonnées données
     * @param x entier représentant la coordonnée en x
     * @param y entier représentant la coordonnée en y
     * @return une GamePiece
     * @pre coordonnées valides (comprises dans le plateau)
     * @post result = plateau[x][y]
     */
    protected GamePiece getPiece(int x, int y) {
        GamePiece result =  null;
        // TODO vérification de la taille du tableau
        if (this.gamePieceBoard[x][y] == null || this.gamePieceBoard[x][y] instanceof PacDot){
            result = this.gameGhostBoard[x][y];
        }
        else {
            result = this.gamePieceBoard[x][y];
        }
        return result;
    }

    protected PacMan getPacMan() {
        return pacMan;
    }
}
