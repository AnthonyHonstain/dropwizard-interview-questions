package com.example.helloworld.adjacencyListGraph;

import java.util.List;

/**
 * Created by Anthony Honstain on 7/8/17.
 */
public class Node {
    private String key;
    private List<Node> adjacent;

    public Node(String key, List<Node> adjacent) {
        this.key = key;
        this.adjacent = adjacent;
    }

    public String getKey() {
        return key;
    }

    public List<Node> getAdjacent() {
        return adjacent;
    }
}
