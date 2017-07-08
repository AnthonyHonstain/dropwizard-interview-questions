package com.example.helloworld.adjacencyListGraph;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anthony Honstain on 7/8/17.
 */
public class AdjacencyList {

    public boolean routeExists(Node start, Node finish) {
        Set<String> visited = new HashSet<>();
        visited.add(start.getKey());
        return depthFirstCheck(start, finish, visited);
    }

    public boolean depthFirstCheck(Node current, Node finish, Set<String> visited) {

        for (Node neighbor : current.getAdjacent()) {
            if (!visited.contains(neighbor.getKey())) {
                visited.add(neighbor.getKey());

                if (neighbor.getKey() == finish.getKey()) {
                    return true;
                }
                if (depthFirstCheck(neighbor, finish, visited)){
                    return true;
                }
            }
        }
        return false;
    }
}
