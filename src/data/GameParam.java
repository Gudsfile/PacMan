package data;

/**
 * Cette classe initialise les paramètre d'une partie de PacMan
 * @author Alexis CANO
 */
public class GameParam {

    /**
     *
     */
    private int regularPacDotValue;
    private int powerPacDotValue;
    private int powerTime;
    private int gameSpeed;
    private Entity[][] board;

    public GameParam(int regularPacDotValue, int powerPacDotValue, int powerTime, int gameSpeed, int[][] dataBoard) {
        this.regularPacDotValue = regularPacDotValue;
        this.powerPacDotValue = powerPacDotValue;
        this.powerTime = powerTime;
        this.gameSpeed = gameSpeed;
        this.board = this.initGameBoard(dataBoard);
    }

    private Entity[][] initGameBoard(int[][] dataBoard) {
        Entity[][] board = new Entity[dataBoard.length][dataBoard.length];
        for (int i = 0; i < dataBoard.length; i++) {
            for (int j = 0; j < dataBoard.length; j++) {
                switch(dataBoard[i][j]) {
                    case 0: this.board[i][j] = null;
                    case 1: this.board[i][j] = new EntityWall();
                    case 2: this.board[i][j] = new EntityRegularPacDot();
                    case 3: this.board[i][j] = new EntityFruit();
                    case 4: this.board[i][j] = new EntitySuperPacDot();
                    case 5: this.board[i][j] = new EntityGhost();
                    case 6: this.board[i][j] = new EntityPacMan();
                }
            }
        }
        return board;
    }

    public int getRegularPacDotValue() {
        return regularPacDotValue;
    }

    public void setRegularPacDotValue(int regularPacDotValue) {
        this.regularPacDotValue = regularPacDotValue;
    }

    public int getPowerPacDotValue() {
        return powerPacDotValue;
    }

    public void setPowerPacDotValue(int powerPacDotValue) {
        this.powerPacDotValue = powerPacDotValue;
    }

    public int getPowerTime() {
        return powerTime;
    }

    public void setPowerTime(int powerTime) {
        this.powerTime = powerTime;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public Entity[][] getBoard() {
        return board;
    }

    public void setBoard(Entity[][] board) {
        this.board = board;
    }
}
