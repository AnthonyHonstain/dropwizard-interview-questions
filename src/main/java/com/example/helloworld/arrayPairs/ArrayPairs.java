package com.example.helloworld.arrayPairs;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayPairs {

    public static Set<Pair<Integer,Integer>> findPairs(List<Integer> input, int sum) {
        Collections.sort(input);
        int head = 0;
        int tail = input.size() - 1;
        Set<Pair<Integer,Integer>> result = new HashSet<>();

        while (head < tail) {
            int tempSum = input.get(head) + input.get(tail);
            if (tempSum == sum) {
                result.add(Pair.of(input.get(head), input.get(tail)));
                head++;
                tail--;
            } else if (tempSum < sum) {
                head++;
            } else {
                tail--;
            }
        }
        return result;
    }
}
