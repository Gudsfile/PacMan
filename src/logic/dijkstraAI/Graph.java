package logic.dijkstraAI;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe représentant un graph orienté
 */
public class Graph {

    /**
     * Liste des noeuds du graph
     */
    private Set<Node> nodes = new HashSet<>();

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
