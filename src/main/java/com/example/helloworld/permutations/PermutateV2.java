package com.example.helloworld.permutations;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.IntStream;

/**
 * Created by Anthony Honstain on 3/5/17.
 */
public class PermutateV2 {

    // Maybe this doesn't actually need to behave like a normal object.
    // Should consider some static methods?
    public PermutateV2() {

    }

    public ArrayList<String> calcuatePermutations(String input) {
        ArrayList<String> results = new ArrayList<>();

        boolean[] indexTracker = new boolean[input.length()];
        // Introducing this notion of a hashset as an example of the added
        // cost of the copy (so even though the code got a little bit simpler
        // in the recursive function, we might have consider the performance
        // tradeoff.
        HashSet<Integer> indexTrackerSet = Sets.newHashSet(
                IntStream.range(0, input.length()).iterator()
        );

        Integer[] inProgress = new Integer[input.length()];

        calcRecur(results, input, indexTracker, indexTrackerSet, inProgress, 0);

        return results;
    }

    public void calcRecur(ArrayList<String> results,
                          String input,
                          boolean[] indexTracker,
                          HashSet<Integer> indexTrackerSet,
                          Integer[] inProgress,
                          int depth) {
        HashSet<Integer> temp = (HashSet)indexTrackerSet.clone();
        for(Integer index : temp) {
            if (!indexTracker[index]) {
                indexTracker[index] = true;
                inProgress[depth] = index;
                indexTrackerSet.remove(index);

                if (depth + 1 == input.length()) {
                    results.add(finalize(input, inProgress));
                }
                else {
                    calcRecur(results, input, indexTracker, indexTrackerSet, inProgress, depth+1);
                }

                indexTracker[index] = false;
                inProgress[depth] = null;
                indexTrackerSet.add(index);
            }

        }
    }

    public String finalize(String input,
                           Integer[] inProgress) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer current : inProgress) {
            stringBuilder.append(input.substring(current, current+1));
        }
        return stringBuilder.toString();
    }
}
