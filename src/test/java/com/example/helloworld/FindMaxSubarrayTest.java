package com.example.helloworld;

import com.example.helloworld.maxSubarray.FindMaxSubarray;
import com.google.common.collect.ImmutableList;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindMaxSubarrayTest {

    @Test
    public void emptyBruteForceLongestSubarray() {
        List<Character> orig = Lists.emptyList();
        List<Character> result = FindMaxSubarray.bruteForceLongestSubarray(orig);
        assertEquals(orig, result);
    }

    @Test
    public void smallBruteForceLongestSubarray() {
        List<Character> orig = ImmutableList.of('A', 'B');
        List<Character> result = FindMaxSubarray.bruteForceLongestSubarray(orig);
        assertEquals(orig, result);

        orig = ImmutableList.of('A', 'A', 'B', 'B');
        result = FindMaxSubarray.bruteForceLongestSubarray(orig);
        assertEquals(orig, result);

        orig = ImmutableList.of('A', 'A', 'B', 'B', 'A', 'A', 'A', 'A');
        result = FindMaxSubarray.bruteForceLongestSubarray(orig);
        assertEquals(ImmutableList.of('A', 'A', 'B', 'B'), result);
    }

    @Test
    public void largeBruteForceLongestSubarray() {
        List<Character> orig = orig = ImmutableList.of('A', 'A', 'A', 'A', 'B', 'B', 'A', 'B', 'B', 'A', 'A',
                'B', 'A', 'A', 'B', 'A', 'A', 'A', 'A');
        List<Character> result = FindMaxSubarray.bruteForceLongestSubarray(orig);
        assertEquals(ImmutableList.of('A', 'B', 'B', 'A', 'B', 'B', 'A', 'A',
                'B', 'A', 'A', 'B'),
                result);
    }
}
