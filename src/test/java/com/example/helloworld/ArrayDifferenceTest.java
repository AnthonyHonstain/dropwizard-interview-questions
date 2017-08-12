package com.example.helloworld;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.example.helloworld.arrayDifference.ArrayDifference.findSmallestDiff;
import static com.example.helloworld.arrayDifference.ArrayDifference.findSmallestDiffTerrible;
import static org.junit.Assert.assertEquals;

public class ArrayDifferenceTest {

    @Test
    public void testFindSmallestDiffBasic() {
        List<Integer> A = Arrays.asList(1, 3, 15, 11, 2);
        List<Integer> B = Arrays.asList(23, 127, 235, 19, 8);

        assertEquals(Integer.valueOf(3), findSmallestDiffTerrible(A, B));
        assertEquals(Integer.valueOf(3), findSmallestDiff(A, B));
    }

    @Test
    public void testFindSmallestDiffSmall() {
        List<Integer> A = Arrays.asList(1, 2, 3, 3, 4);
        List<Integer> B = Arrays.asList(3);

        assertEquals(Integer.valueOf(0), findSmallestDiffTerrible(A, B));
        assertEquals(Integer.valueOf(0), findSmallestDiff(A, B));

        A = Arrays.asList(3);
        B = Arrays.asList(1, 2, 3, 3, 4);

        assertEquals(Integer.valueOf(0), findSmallestDiffTerrible(A, B));
        assertEquals(Integer.valueOf(0), findSmallestDiff(A, B));
    }
}
