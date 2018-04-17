package logic.dijkstraAI;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public Node getShortestPathToNode(int x, int y) {
        for (Node node : this.nodes) {
            for (Node node2 : node.getShortestPath()) {
                if (node2.getX() == x && node2.getY() == y) {
                    return node2;
                }
            }
        }
        return null;
    }

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
