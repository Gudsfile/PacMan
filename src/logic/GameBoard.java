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
    private GamePiece[][] gamePieceBoard;
    private Ghost[][] gameGhostBoard;

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
                        gamePieceBoard[i][j] = new PacMan(gameParam.getGameSpeed());
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
     * Deplace un fantôme sur le plateau
     * @param dx déplacement en x de la piece
     * @param dy déplacement en y de la piece
     * @param x position en x de la piece
     * @param y position en y de la piece
     * @pre
     */
    public void move(int dx, int dy, int x, int y) {
        if (isValidMove(x + dx, y + dy)) {
            if (getPiece(x, y) instanceof Ghost) {
                moveGhost(dx, dy, x, y);
            } else if (getPiece(x, y) instanceof PacMan) {
                movePacMan(dx, dy, x, y);
            }
        }
    }

    /**
     * Vérifie la validité d'une position
     * @param x position en x
     * @param y position en y
     * @return un booléen indiquant la validité de la position donnée
     */
    private boolean isValidMove(int x, int y) {
        boolean result = true;

        if (x < 0 || x > gamePieceBoard.length) {
            result = false;
        } else if (y < 0 || y > gamePieceBoard[0].length) {
            result = false;
        } else if (gamePieceBoard[x][y] instanceof Wall) {
            result = false;
        }

        return result;
    }

    /**
     * Déplace un fantôme
     * @param dx déplacement en x du fantôme
     * @param dy déplacement en y du fantôme
     * @param x position en x du fantôme
     * @param y position en y du fantôme
     * @pre piece instanceof Ghost
     * @pre x+dx and y+dy in the board
     * @post piece.x = x+dx and piece.y = y+dy
     */
    private void moveGhost(int dx, int dy, int x, int y) {
        gameGhostBoard[x+dx][y+dy] = gameGhostBoard[x][y];
        gameGhostBoard[x][y] = null;
    }

    /**
     * Déplace un PacMan
     * @param dx déplacement en x du PacMan
     * @param dy déplacement en y du PacMan
     * @param x position en x du PacMan
     * @param y position en y du PacMan
     * @pre piece instanceof PacMan
     * @pre x+dx and y+dy in the board
     * @post piece.x = x+dx and piece.y = y+dy
     */

    private void movePacMan(int dx, int dy, int x, int y) {
        //TODO Augmenter le score si pacdot + power ?
        //TODO Cas où Fantôme ? Fantôme + Power ?
        gamePieceBoard[x+dx][y+dy] = gamePieceBoard[x][y];
        gamePieceBoard[x][y] = null;
    }

    /**
     * Supprime une piece du plateau
     * @param x position de la piece en x
     * @param y position de la piece en y
     * @pre piece instanceof PacDot
     * @post gamePieceBoard[x][y] = null
     */
    private void erase(int x, int y) {
        gamePieceBoard[x][y] = null;
    }

    /**
     * Retourne la pièce aux coordonnées données
     * @param x entier représentant la coordonnée en x
     * @param y entier représentant la coordonnée en y
     * @return une GamePiece
     * @pre coordonnées valides (comprises dans le plateau)
     * @post result = plateau[x][y]
     */
    private GamePiece getPiece(int x, int y) {
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
}
