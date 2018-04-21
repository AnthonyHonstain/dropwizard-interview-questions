package com.example.helloworld.maxSubarray;

import org.assertj.core.util.Lists;

import java.util.List;

public class FindMaxSubarray {

    public static final Character A = 'A';
    public static final Character B = 'B';

    public static List<Character> bruteForceLongestSubarray(List<Character> input) {
        for (int len = input.size(); len > 1; len--) {
            for (int i = 0; i <= input.size() - len; i++) {
                if (hasEqualAandB(input, i, i + len - 1)) {
                    return input.subList(i, i + len);
                }
            }
        }
        return Lists.emptyList();
    }

    public static boolean hasEqualAandB(List<Character> input, int start, int end) {
        int counter = 0;
        for (int i = start; i <= end; i++) {
            if (input.get(i) == A) {
                counter++;
            } else {
                counter--;
            }
        }
        return counter == 0;
    }
}
