package com.example.helloworld;

import com.example.helloworld.adjacencyListGraph.AdjacencyList;
import com.example.helloworld.adjacencyListGraph.Node;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Anthony Honstain on 7/8/17.
 */
public class AdjacencyListTest {

    @Test
    public void depthFirstCheckSimpleTest() {
        Node a = new Node("A", new ArrayList<>());
        Node b = new Node("B", new ArrayList<>());
        a.getAdjacent().add(b);

        Map<String, Node> graph = ImmutableMap.of(
                a.getKey(), a,
                b.getKey(), b
        );
        AdjacencyList adjacencyList = new AdjacencyList();

        Set<String> visited = new HashSet<>();
        assertTrue(adjacencyList.depthFirstCheck(a, b, visited));
        assertEquals(ImmutableSet.of("B"), visited);
    }

    @Test
    public void depthFirstCheckTest() {
        Node a = new Node("A", new ArrayList<>());
        Node b = new Node("B", new ArrayList<>());
        Node c = new Node("C", new ArrayList<>());
        a.getAdjacent().add(b);
        b.getAdjacent().add(c);

        Map<String, Node> graph = ImmutableMap.of(
                a.getKey(), a,
                b.getKey(), b,
                c.getKey(), c
        );
        AdjacencyList adjacencyList = new AdjacencyList();

        Set<String> visited = new HashSet<>();
        assertTrue(adjacencyList.depthFirstCheck(a, b, visited));
        assertEquals(ImmutableSet.of("B"), visited);

        visited = new HashSet<>();
        assertTrue(adjacencyList.depthFirstCheck(a, c, visited));
        assertEquals(ImmutableSet.of("B", "C"), visited);
    }

    @Test
    public void depthFirstCheckCycleTest() {
        Node a = new Node("A", new ArrayList<>());
        Node b = new Node("B", new ArrayList<>());
        Node c = new Node("C", new ArrayList<>());
        a.getAdjacent().add(b);
        a.getAdjacent().add(c);
        b.getAdjacent().add(a);

        Map<String, Node> graph = ImmutableMap.of(
                a.getKey(), a,
                b.getKey(), b,
                c.getKey(), c
        );
        AdjacencyList adjacencyList = new AdjacencyList();

        Set<String> visited = new HashSet<>();
        assertTrue(adjacencyList.depthFirstCheck(a, c, visited));
        assertEquals(ImmutableSet.of("A", "B", "C"), visited);
    }

    @Test
    public void routeExistsTest() {
        Node a = new Node("A", new ArrayList<>());
        Node b = new Node("B", new ArrayList<>());
        Node c = new Node("C", new ArrayList<>());
        a.getAdjacent().add(b);
        a.getAdjacent().add(c);
        b.getAdjacent().add(a);

        Map<String, Node> graph = ImmutableMap.of(
                a.getKey(), a,
                b.getKey(), b,
                c.getKey(), c
        );
        AdjacencyList adjacencyList = new AdjacencyList();

        assertTrue(adjacencyList.routeExists(a, c));
    }

    @Test
    public void routeExistsFalseTest() {
        Node a = new Node("A", new ArrayList<>());
        Node b = new Node("B", new ArrayList<>());
        Node c = new Node("C", new ArrayList<>());
        a.getAdjacent().add(b);
        c.getAdjacent().add(b);

        Map<String, Node> graph = ImmutableMap.of(
                a.getKey(), a,
                b.getKey(), b,
                c.getKey(), c
        );
        AdjacencyList adjacencyList = new AdjacencyList();

        assertFalse(adjacencyList.routeExists(a, c));
    }
}
