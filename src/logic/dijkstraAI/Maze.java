package logic.dijkstraAI;

import logic.GamePiece;
import logic.Ghost;

/**
 * Cette clase représente,
 */
public class Maze {

    /**
     * Un tableau 2D, chaque case représente le node qui correspond à une case du plateau de jeu
     */
    Node[][] nodeBoard;
    /**
     *
     */
    Graph graph;

    public Maze(GamePiece[][] gameBoard){
        this.nodeBoard = new Node[gameBoard.length][gameBoard[0].length];
        this.graph = new Graph();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                if (!(gameBoard[i][j] instanceof logic.Wall)) {
                    this.nodeBoard[i][j] = new Node(i, j);
                }
            }
        }
        for (int i = 0; i < this.nodeBoard.length; i++) {
            for (int j = 0; j < this.nodeBoard[0].length; j++) {
                if (this.nodeBoard[i][j] != null) {
                    if (i == 9 && j == 0) {
                        this.nodeBoard[i][j].addDestination(this.nodeBoard[i][j+1], 1);
                        this.nodeBoard[i][j].addDestination(this.nodeBoard[i][18], 1);
                    } else if (i == 9 && j == 18) {
                        this.nodeBoard[i][j].addDestination(this.nodeBoard[i][j-1], 1);
                        this.nodeBoard[i][j].addDestination(this.nodeBoard[i][0], 1);
                    } else {
                        if (this.nodeBoard[i+1][j] != null) {
                            this.nodeBoard[i][j].addDestination(this.nodeBoard[i+1][j], 1);
                        }
                        if (this.nodeBoard[i-1][j] != null) {
                            this.nodeBoard[i][j].addDestination(this.nodeBoard[i-1][j], 1);
                        }
                        if (this.nodeBoard[i][j+1] != null) {
                            this.nodeBoard[i][j].addDestination(this.nodeBoard[i][j+1], 1);
                        }
                        if (this.nodeBoard[i][j-1] != null) {
                            this.nodeBoard[i][j].addDestination(this.nodeBoard[i][j-1], 1);
                        }
                    }
                    this.graph.addNode(this.nodeBoard[i][j]);
                }
            }
        }
    }

    public Node getShortestPath(int xStart, int yStart, int xEnd, int yEnd){
        Node res = null;
        if (Math.abs(xStart - xEnd) == 1 || Math.abs(yStart - yEnd) == 1) {
            return null;
        } else {
            Graph shortestPathGraph = Dijkstra.calculateShortestPathFromSource(this.graph, this.nodeBoard[xStart][yStart]);
            for (Node node : shortestPathGraph.getNodes()) {
                if(node.getX() == xEnd && node.getY() == yEnd) {
                    res = node;
                }
            }
        }
        return res;
    }
}
