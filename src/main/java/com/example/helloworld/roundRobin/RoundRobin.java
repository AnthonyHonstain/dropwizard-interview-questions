package com.example.helloworld.roundRobin;

import java.util.ArrayList;
import java.util.List;

/**
 * A white board algorithm problem to take a list of lists of elements and create a basic algorithm
 * to iterate through them in round robin order until every element is returned once.
 *
 * Examples:
 *   [[A],[B,C]] -> [A,B,C]
 *   [[A,D], [B,E,G,H], [C,F]] -> [A,B,C,D,E,F,G,H]
 */
public class RoundRobin {

    private List<List<String>> roundRobinLists;
    private List<Integer> subArrayIndices;
    private Integer currentSubArrayIndex;

    public RoundRobin(List<List<String>> roundRobinLists) {
        this.roundRobinLists = roundRobinLists;
        this.subArrayIndices = new ArrayList<>();
        for (List<String> x: roundRobinLists) {
            this.subArrayIndices.add(-1);
        }
        // TODO - assumes some roundRobinLists
        this.subArrayIndices.set(0, 0);

        this.currentSubArrayIndex = 0;
    }

    public boolean hasNext() {
        return isSubArrayValid();
    }

    private void incrementCurrent() {
        if(currentSubArrayIndex == roundRobinLists.size() - 1) {
            currentSubArrayIndex = 0;
        } else {
            currentSubArrayIndex += 1;
        }
    }

    private void incSubArray() {
        int value = subArrayIndices.get(currentSubArrayIndex);
        subArrayIndices.set(currentSubArrayIndex, value + 1);
    }

    private boolean isSubArrayValid() {
        return (subArrayIndices.get(currentSubArrayIndex) < roundRobinLists.get(currentSubArrayIndex).size());
    }

    public String next() {
        if(!isSubArrayValid()) {
            throw new RuntimeException("No Next Value");
        }
        String result = roundRobinLists.get(currentSubArrayIndex).get(subArrayIndices.get(currentSubArrayIndex));

        incrementCurrent();
        incSubArray();
        if(isSubArrayValid()) {
            return result;
        }

        int cycleDetect = currentSubArrayIndex;
        incrementCurrent();

        while(cycleDetect != currentSubArrayIndex) {
            incSubArray();
            if (isSubArrayValid()) {
                return result;
            }
            incrementCurrent();
        }
        return result;
    }
}
