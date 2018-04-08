package data;

/**
 * Cette class permet de stocker les paramètre d'une partie de PacMan lu à partir d'un fichier JSON de paramètre
 * @author Alexis CANO
 */
public class GameParam {

    /**
     * Le niveau de jeu
     */
    private int level;
    /**
     * Le nombre de point pour un PacDot
     */
    private int pacDotValue;
    /**
     * Le nombre de point pour un Fruit
     */
    private int fruitValue;
    /**
     * Le temps en seconde du Power
     */
    private int powerTime;
    /**
     * La vitesse du jeu
     */
    private int gameSpeed;
    /**
     * Le plateau de jeu contenant les différentes pièces (PacDot, Fruit, PacMan)
     */
    private Entity[][] board;
    /**
     * Le plateau de jeu contenant seulement les fantômes
     */
    private EntityGhost[][] ghostBoard;

    /**
     * Constructeur, initialise les attribut de classe
     * @param level le niveau de jeu
     * @param pacDotValue le nombre de point pour un PacDot
     * @param fruitValue le nombre de point pour un Fruit
     * @param powerTime le temps en seconde du power
     * @param gameSpeed la vitesse du jeu
     * @param dataBoard le plateau de jeu (tableau d'entier, 0 = vide, 1 = wall, 2 = regularPacDot, 3 = fruit, 4 = SuperPacDot, 5 = ghost, 6 = pacMan
     */
    public GameParam(int level, int pacDotValue, int fruitValue, int powerTime, int gameSpeed, int[][] dataBoard) {
        this.level = level;
        this.pacDotValue = pacDotValue;
        this.fruitValue = fruitValue;
        this.powerTime = powerTime;
        this.gameSpeed = gameSpeed;
        this.board = initGameBoard(dataBoard);
    }

    /**
     * Initialise le plateau de jeu avec des objets entité
     * @param dataBoard le plateau de jeu sous forme d'entier [0-6]
     * @return Un tableau d'entité correspondant au tableau passé en paramètre
     */
    private Entity[][] initGameBoard(int[][] dataBoard) {
        EntityGhost.compteurGhost = 0;
        this.board = new Entity[dataBoard.length][dataBoard[0].length];
        this.ghostBoard = new EntityGhost[dataBoard.length][dataBoard[0].length];
        for (int i = 0; i < dataBoard.length; i++) {
            for (int j = 0; j < dataBoard[1].length; j++) {
                switch(dataBoard[i][j]) {
                    case 0:
                        this.board[i][j] = null;
                        break;
                    case 1:
                        this.board[i][j] = new EntityWall();
                        break;
                    case 2:
                        this.board[i][j] = new EntityRegularPacDot();
                        break;
                    case 3:
                        this.board[i][j] = new EntityFruit(this.level);
                        break;
                    case 4:
                        this.board[i][j] = new EntitySuperPacDot();
                        break;
                    case 5:
                        this.ghostBoard[i][j] = new EntityGhost();
                        break;
                    case 6:
                        this.board[i][j] = new EntityPacMan();
                        break;
                }
            }
        }
        return board;
    }

    /**
     * Get pacDotValue
     * @return pacDotValue
     */
    public int getPacDotValue() {
        return pacDotValue;
    }

    /**
     * Set pacDotValue
     * @param pacDotValue new pacDotValue
     */
    public void setPacDotValue(int pacDotValue) {
        this.pacDotValue = pacDotValue;
    }

    /**
     * Get fruitValue
     * @return fruitValue
     */
    public int getFruitValue() {
        return fruitValue;
    }

    /**
     * Set fruitValue
     * @param fruitValue new fruitValue
     */
    public void setFruitValue(int fruitValue) {
        this.fruitValue = fruitValue;
    }

    /**
     * Get powerTime
     * @return powerTime
     */
    public int getPowerTime() {
        return powerTime;
    }

    /**
     * Set powerTime
     * @param powerTime new powerTime
     */
    public void setPowerTime(int powerTime) {
        this.powerTime = powerTime;
    }

    /**
     * Get gameSpeed
     * @return gameSpeed
     */
    public int getGameSpeed() {
        return gameSpeed;
    }

    /**
     * Set gameSpeed
     * @param gameSpeed new gameSpeed
     */
    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    /**
     * Get board
     * @return board
     */
    public Entity[][] getBoard() {
        return board;
    }

    /**
     * Set board
     * @param board new board
     */
    public void setBoard(Entity[][] board) {
        this.board = board;
    }

    /**
     * Get ghostBoard
     * @return ghostBoard
     */
    public EntityGhost[][] getGhostBoard() {
        return ghostBoard;
    }

    /**
     * Set ghostBoard
     * @param ghostBoard new ghostBoard
     */
    public void setGhostBoard(EntityGhost[][] ghostBoard) {
        this.ghostBoard = ghostBoard;
    }
}
