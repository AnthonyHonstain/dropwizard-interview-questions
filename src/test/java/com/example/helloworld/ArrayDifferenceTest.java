package com.example.helloworld;

import com.example.helloworld.ArrayDifference.ArrayDifference;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArrayDifferenceTest {

    @Test
    public void testFindSmallestDiffBasic() {
        List<Integer> A = Arrays.asList(1, 3, 15, 11, 2);
        List<Integer> B = Arrays.asList(23, 127, 235, 19, 8);
        ArrayDifference arrayDifference = new ArrayDifference();

        assertEquals(Integer.valueOf(3), arrayDifference.findSmallestDiffTerrible(A, B));
    }
}
