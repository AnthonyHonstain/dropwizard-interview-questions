package com.example.helloworld;

import com.example.helloworld.arrayPairs.ArrayPairs;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ArrayPairsTest {

    @Test
    public void testEmptyList() {
        List<Integer> input = Lists.newArrayList();
        Set<Pair<Integer,Integer>> result = ArrayPairs.findPairs(input, 3);
        assertEquals(result, ImmutableSet.of());
    }

    @Test
    public void testSimpleList() {
        List<Integer> input = Lists.newArrayList(1,7,4,4);
        Set<Pair<Integer,Integer>> result = ArrayPairs.findPairs(input, 8);
        assertEquals(result, ImmutableSet.of(Pair.of(1,7), Pair.of(4,4)));
    }

    @Test
    public void testSimpleWithExtraElements() {
        List<Integer> input = Lists.newArrayList(1,7,2,4,2,4,8);
        Set<Pair<Integer,Integer>> result = ArrayPairs.findPairs(input, 8);
        assertEquals(result, ImmutableSet.of(Pair.of(1,7), Pair.of(4,4)));
    }

    @Test
    public void testNoPairs() {
        List<Integer> input = Lists.newArrayList(1,7,4,4);
        Set<Pair<Integer,Integer>> result = ArrayPairs.findPairs(input, 3);
        assertEquals(result, ImmutableSet.of());
    }
}
