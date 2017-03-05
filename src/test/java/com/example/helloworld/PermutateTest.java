package com.example.helloworld;

import com.example.helloworld.permutations.Permutate;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anthony Honstain on 3/4/17.
 */
public class PermutateTest {

    @Test
    public void largePermutationTest() {
        Permutate permutate = new Permutate();
        ArrayList<String> results = permutate.calcuatePermutations("ABCD");

        assertEquals(
                ImmutableList.of(
                        "ABCD", "ABDC", "ACBD", "ACDB", "ADBC", "ADCB",
                        "BACD", "BADC", "BCAD", "BCDA", "BDAC", "BDCA",
                        "CABD", "CADB", "CBAD", "CBDA", "CDAB", "CDBA",
                        "DABC", "DACB", "DBAC", "DBCA", "DCAB", "DCBA"),
                results
        );
    }

    @Test
    public void smallPermutationsTests() {
        Permutate permutate = new Permutate();
        ArrayList<String> results = permutate.calcuatePermutations("A");
        assertEquals(ImmutableList.of("A"), results);

        results = permutate.calcuatePermutations("AB");
        assertEquals(ImmutableList.of("AB", "BA"), results);

        results = permutate.calcuatePermutations("AA");
        assertEquals(ImmutableList.of("AA", "AA"), results);

        results = permutate.calcuatePermutations("ABC");
        assertEquals(
                ImmutableList.of("ABC", "ACB", "BAC", "BCA", "CAB", "CBA"),
                results
        );
    }

    @Test
    public void calcRecurBasic() {
        Permutate permutate = new Permutate();
        ArrayList<String> results = new ArrayList<>();
        permutate.calcRecur(
                results,
                "A",
                new boolean[] {false},
                new Integer[] {null},
                0
                );
        assertEquals(ImmutableList.of("A"), results);
    }

    @Test
    public void calcRecurSizeTwo() {
        Permutate permutate = new Permutate();
        ArrayList<String> results = new ArrayList<>();
        permutate.calcRecur(
                results,
                "AB",
                new boolean[] {false, false},
                new Integer[] {null, null},
                0
        );
        assertEquals(ImmutableList.of("AB", "BA"), results);
    }

    @Test
    public void calcRecurSizeThree() {
        Permutate permutate = new Permutate();
        ArrayList<String> results = new ArrayList<>();
        permutate.calcRecur(
                results,
                "ABC",
                new boolean[] {false, false, false},
                new Integer[] {null, null, null},
                0
        );
        assertEquals(
                ImmutableList.of("ABC", "ACB", "BAC", "BCA", "CAB", "CBA"),
                results
        );
    }

    @Test
    public void finalizeBasic() {
        Permutate permutate = new Permutate();
        assertEquals("A", permutate.finalize("A", new Integer[] {0}));
        assertEquals("AB", permutate.finalize("AB", new Integer[] {0, 1}));
        assertEquals("BA", permutate.finalize("AB", new Integer[] {1, 0}));
    }

    @Test
    public void finalizeComplicated() {
        Permutate permutate = new Permutate();
        assertEquals(
                "BCAD",
                permutate.finalize("ABCD", new Integer[] {1, 2, 0, 3})
        );
    }
}
