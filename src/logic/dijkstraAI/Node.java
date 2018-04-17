package logic.dijkstraAI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    /**
     * Position x
     */
    private int x;
    /**
     * Position y
     */
    private int y;
    /**
     * Liste chainé
     */
    private List<Node> shortestPath = new LinkedList<>();

    /**
     * Distane infinie utilisé pour initialser le graph des parcours
     */
    private Integer distance = Integer.MAX_VALUE;
    /**
     * Hashmap contenant les couples (Node adjacent, distance) du node
     */
    Map<Node, Integer> adjacentNodes = new HashMap<>();

    private String name;

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(String name) {
        this.name = name;
    }

    public Node(int x, int y) {
        this.name = x + ":" + y;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}