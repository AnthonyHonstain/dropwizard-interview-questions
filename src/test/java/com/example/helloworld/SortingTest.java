package com.example.helloworld;

import com.example.helloworld.interviewSort.Sorting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortingTest {

    @Test
    public void basic() {
        List<Integer> result = Sorting.inPlaceMerge(
                Lists.newArrayList(1, 3, null, null),
                Lists.newArrayList(2, 4)
        );
        assertEquals(ImmutableList.of(1,2,3,4), result);
    }


    @Test
    public void emptyArrayToMergeFrom() {
        List<Integer> result = Sorting.inPlaceMerge(
                Lists.newArrayList(1, 2),
                Lists.newArrayList()
        );
        assertEquals(ImmutableList.of(1,2), result);
    }

    @Test
    public void emptyArrayToMergeTo() {
        List<Integer> result = Sorting.inPlaceMerge(
                Lists.newArrayList(null, null),
                Lists.newArrayList(1, 2)
        );
        assertEquals(ImmutableList.of(1,2), result);
    }
}
