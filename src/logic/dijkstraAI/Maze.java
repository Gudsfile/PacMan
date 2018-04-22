package logic.dijkstraAI;

/**
 * Cette classe permet de créer un graphe à partir d'un tableau à deux entrées et d'obtenir le chemin le plus
 * court d'un point (x,y) vers un point demandé
 */
public class Maze {

    /**
     * Tableau des noeuds
     */
    private Node[][] nodeBoard;
    /**
     * Graphe correspondant
     */
    private Graph graph;
    /**
     * coordonnée x de départ des chemins
     */
    private int x;
    /**
     * coordonnée y de départ des chemins
     */
    private int y;

    /**
     * Construit un objet Maze, initialise le tableau des noeuds et créer le graph
     *
     * @param board tableau à deux dimensions
     * @param x     coordonée x du point de départ des chemins à calculer
     * @param y     coordonée y du point de départ des chemins à calculer
     * @pre board != null
     * @pre x comprit dans le tableau
     * @pre y comprit dans le tableau
     * @post si un élement de board vaut 1 alors il n'est pas accessible
     * @post graph correspondant au point (x,y) et à board
     */
    public Maze(int[][] board, int x, int y) {
        this.x = x;
        this.y = y;
        this.nodeBoard = new Node[board.length][board[0].length];

        this.graph = new Graph();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 1) {
                    this.nodeBoard[i][j] = new Node(i, j);
                }
            }
        }

        for (int i = 0; i < this.nodeBoard.length; i++) {
            for (int j = 0; j < this.nodeBoard[0].length; j++) {
                if (this.nodeBoard[i][j] != null) {
                    if (j == 0) {
                        this.nodeBoard[i][j].addDestination(this.nodeBoard[i][j + 1], 1);
                        this.nodeBoard[i][j].addDestination(this.nodeBoard[i][this.nodeBoard[0].length - 1], 1);
                    } else if (j == this.nodeBoard[0].length - 1) {
                        this.nodeBoard[i][j].addDestination(this.nodeBoard[i][j - 1], 1);
                        this.nodeBoard[i][j].addDestination(this.nodeBoard[i][0], 1);
                    } else {
                        if (this.nodeBoard[i][j + 1] != null) {
                            this.nodeBoard[i][j].addDestination(this.nodeBoard[i][j + 1], 1);
                        }
                        if (this.nodeBoard[i][j - 1] != null) {
                            this.nodeBoard[i][j].addDestination(this.nodeBoard[i][j - 1], 1);
                        }
                        if (this.nodeBoard[i - 1][j] != null) {
                            this.nodeBoard[i][j].addDestination(this.nodeBoard[i - 1][j], 1);
                        }
                        if (this.nodeBoard[i + 1][j] != null) {
                            this.nodeBoard[i][j].addDestination(this.nodeBoard[i + 1][j], 1);
                        }
                    }
                    this.graph.addNode(this.nodeBoard[i][j]);
                }
            }
        }

        graph = Dijkstra.calculateShortestPathFromSource(this.graph, this.nodeBoard[this.x][this.y]);

    }

    /**
     * Retourne le chemin le plus court allant de (x,y) à (xEnd, yEnd)
     *
     * @param xEnd coordonnée x du point à atteindre
     * @param yEnd coordonnée y du point à atteindre
     * @return le chemin le plus court vers (xEnd, yEnd)
     * @post result = null si pas de chemins trouvé
     */
    public Node getPath(int xEnd, int yEnd) {
        Node res = null;
        for (Node node : this.graph.getNodes()) {
            if (node.getName().equals(xEnd + ":" + yEnd)) {
                res = node;
            }
        }
        return res;
    }


}
