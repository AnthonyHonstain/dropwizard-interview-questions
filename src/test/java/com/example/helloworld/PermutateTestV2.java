package com.example.helloworld;

import com.example.helloworld.permutations.Permutate;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anthony Honstain on 3/5/17.
 */
public class PermutateTestV2 {

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
}
