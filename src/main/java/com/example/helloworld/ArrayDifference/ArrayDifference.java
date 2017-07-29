package com.example.helloworld.ArrayDifference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayDifference {

    public class MergeObject {
        private int value;
        private String source;

        public MergeObject(int value, String source) {
            this.value = value;
            this.source = source;
        }
    }

    public List<MergeObject> sortedMerge(List<Integer> A,List<Integer> B) {
        int indexA = 0;
        int indexB = 0;
        List<MergeObject> result = new ArrayList<>();

        while (indexA < A.size() || indexB < B.size()) {
            if (indexA < A.size() && indexB < B.size()) {
                if (A.get(indexA) < B.get(indexB)) {
                    result.add(new MergeObject(A.get(indexA), "A"));
                    indexA++;
                } else {
                    result.add(new MergeObject(B.get(indexB), "B"));
                    indexB++;
                }
            } else if (indexA < A.size()) {
                result.add(new MergeObject(A.get(indexA), "A"));
                indexA++;
            } else {
                result.add(new MergeObject(B.get(indexB), "B"));
                indexB++;
            }
        }
        return result;
    }

    /**
     * This is a horrible solution, came up with when I was really tired and it
     * is clearly a sub-optimal solution. You win some and you loose some I guess.
     * I added a bunch of needless complexity, maybe because I was thinking about
     * logging, or maybe because I was trying to prepare for a generalized solution
     * to support more than two arrays, who knows, but it is certainly wrong.
     */
    public Integer findSmallestDiffTerrible(List<Integer> A, List<Integer> B) {
        Integer result = null;
        Collections.sort(A);
        Collections.sort(B);
        List<MergeObject> mergeObjects = sortedMerge(A, B);
        MergeObject prev = null;

        for (MergeObject element : mergeObjects) {
            if (prev == null) {
                prev = element;
                continue;
            }
            if (element.source != prev.source) {
                int diff = Math.abs(element.value - prev.value);
                if (result == null) {
                    result = diff;
                } else if (diff < result) {
                    result = diff;
                }
            }
            prev = element;
        }
        return result;
    }
}
