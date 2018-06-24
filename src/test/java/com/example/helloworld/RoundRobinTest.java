package com.example.helloworld;

import com.example.helloworld.roundRobin.RoundRobin;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoundRobinTest {

    @Test
    public void constructTest() {
        List<List<String>> elements = ImmutableList.of(
                ImmutableList.of("A", "B"),
                ImmutableList.of("A", "B", "C")
        );
        RoundRobin roundRobin = new RoundRobin(elements);
    }

    @Test(expected = RuntimeException.class)
    public void constructTestEmpty() {
        List<List<String>> elements = ImmutableList.of();
        RoundRobin roundRobin = new RoundRobin(elements);
        // TODO - I am playing fast and loose with the exception handling, leaving this commented out
        // TODO - I should be more concrete around errors on construction, hasNext, next
        //assertFalse(roundRobin.hasNext());
    }

    @Test
    public void constructTestMultipleEmpty() {
        List<List<String>> elements = ImmutableList.of(
                ImmutableList.of(),
                ImmutableList.of()
        );
        RoundRobin roundRobin = new RoundRobin(elements);
        assertFalse(roundRobin.hasNext());
    }

    @Test
    public void hasNextInitial() {
        List<List<String>> elements = ImmutableList.of(
                ImmutableList.of("A", "B"),
                ImmutableList.of("A", "B", "C")
        );
        RoundRobin roundRobin = new RoundRobin(elements);
        assertTrue(roundRobin.hasNext());
    }

    @Test
    public void nextSingleElement() {
        List<List<String>> elements = ImmutableList.of(
                ImmutableList.of("A")
        );
        RoundRobin roundRobin = new RoundRobin(elements);
        assertTrue(roundRobin.hasNext());
        assertEquals("A", roundRobin.next());
    }

    @Test(expected = RuntimeException.class)
    public void singleElementHasNextException() {
        List<List<String>> elements = ImmutableList.of(
                ImmutableList.of("A")
        );
        RoundRobin roundRobin = new RoundRobin(elements);
        assertEquals("A", roundRobin.next());
        assertFalse(roundRobin.hasNext());
        roundRobin.next();
    }

    @Test
    public void twoSubarrays() {
        List<List<String>> elements = ImmutableList.of(
                ImmutableList.of("A"),
                ImmutableList.of("B")
        );
        RoundRobin roundRobin = new RoundRobin(elements);
        assertTrue(roundRobin.hasNext());
        assertEquals("A", roundRobin.next());

        assertTrue(roundRobin.hasNext());
        assertEquals("B", roundRobin.next());
        assertFalse(roundRobin.hasNext());
    }

    @Test
    public void twoSubArraysMultipleElements() {
        List<List<String>> elements = ImmutableList.of(
                ImmutableList.of("A","C"),
                ImmutableList.of("B","D")
        );
        RoundRobin roundRobin = new RoundRobin(elements);

        assertEquals("A", roundRobin.next());
        assertEquals("B", roundRobin.next());
        assertEquals("C", roundRobin.next());
        assertEquals("D", roundRobin.next());
        assertFalse(roundRobin.hasNext());
    }

    @Test
    public void threeDifferentSizedSubArrays() {
        List<List<String>> elements = ImmutableList.of(
                ImmutableList.of("A","D"),
                ImmutableList.of("B","E","G","H"),
                ImmutableList.of("C","F")
        );
        RoundRobin roundRobin = new RoundRobin(elements);

        List<String> result = new ArrayList<>();
        while(roundRobin.hasNext()) {
            result.add(roundRobin.next());
        }

        assertEquals(ImmutableList.of("A","B","C","D","E","F","G","H"), result);
    }

    @Test
    public void fourDifferentSizedSubArrays() {
        List<List<String>> elements = ImmutableList.of(
                ImmutableList.of("A"),
                ImmutableList.of("B","E"),
                ImmutableList.of("C","F","G"),
                ImmutableList.of("D")
        );
        RoundRobin roundRobin = new RoundRobin(elements);

        List<String> result = new ArrayList<>();
        while(roundRobin.hasNext()) {
            result.add(roundRobin.next());
        }

        assertEquals(ImmutableList.of("A","B","C","D","E","F","G"), result);
    }
}
