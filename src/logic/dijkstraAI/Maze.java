package logic.dijkstraAI;

public class Maze {

    private Node[][] nodeBoard;
    private Graph graph;
    private int x;
    private int y;

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
