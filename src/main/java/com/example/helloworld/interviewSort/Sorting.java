package com.example.helloworld.interviewSort;

import java.util.List;

public class Sorting {

    public static List<Integer> inPlaceMerge(List<Integer> paddedArray, List<Integer> B) {
        int bTail = B.size() - 1;
        int aTail = paddedArray.size() - B.size() - 1;
        int paddedArrayTail = paddedArray.size() - 1; // Where we insert elements to

        // You only need to go as far as you still have elements in B
        while (bTail >= 0) {
            if (aTail >= 0 && paddedArray.get(aTail) > B.get(bTail)) {
                paddedArray.set(paddedArrayTail, paddedArray.get(aTail));
                aTail--;
            }
            else {
                paddedArray.set(paddedArrayTail, B.get(bTail));
                bTail--;
            }
            paddedArrayTail--;
        }
        return paddedArray;
    }
}
