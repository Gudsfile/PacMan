package data;

import logic.GameBoard;
import logic.Ghost;
import logic.PacDot;
import logic.PacMan;

public class GameParam {

    private int nbLife;
    private Ghost[] ghostList;
    private PacDot[] pacDotList;
    private int[][] gameBoard;
    private int[][] ghostBoard;
    private PacMan pacMan;

    public GameParam(int nbLife) {
    }
}
