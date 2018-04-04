package data;

public class GameParam {

    private int gameLevel;
    private int nbLife;
    private int regularPacDotValue;
    private int powerPacDotValue;
    private int powerTime;
    private int gameSpeed;
    private Entity[][] board;

    public GameParam(int gameLevel, int nbLife, int regularPacDotValue, int powerPacDotValue, int[][] dataBoard) {
        this.gameLevel = gameLevel;
        this.nbLife = nbLife;
        this.regularPacDotValue = regularPacDotValue;
        this.powerPacDotValue = powerPacDotValue;
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
                    case 3: this.board[i][j] = new EntityPowerPacDot();
                    case 4: this.board[i][j] = new EntityGhost();
                    case 5: this.board[i][j] = new EntityPacMan();
                }
            }
        }
        return board;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getNbLife() {
        return nbLife;
    }

    public void setNbLife(int nbLife) {
        this.nbLife = nbLife;
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

    public Entity[][] getBoard() {
        return board;
    }

    public void setBoard(Entity[][] board) {
        this.board = board;
    }
}
