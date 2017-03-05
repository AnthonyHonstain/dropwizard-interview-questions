package com.example.helloworld.permutations;

import java.util.ArrayList;

/**
 * Created by Anthony Honstain on 3/4/17.
 */
public class Permutate {

    // Maybe this doesn't actually need to behave like a normal object.
    // Should consider some static methods?
    public Permutate() {

    }

    public ArrayList<String> calcuatePermutations(String input) {
        ArrayList<String> results = new ArrayList<>();

        boolean[] indexTracker = new boolean[input.length()];
        Integer[] inProgress = new Integer[input.length()];

        calcRecur(results, input, indexTracker, inProgress, 0);

        return results;
    }

    public void calcRecur(ArrayList<String> results,
                             String input,
                             boolean[] indexTracker,
                             Integer[] inProgress,
                             int depth) {
        for(int index = 0; index<input.length(); index++) {
            if (!indexTracker[index]) {
                indexTracker[index] = true;
                inProgress[depth] = index;

                if (depth + 1 == input.length()) {
                    results.add(finalize(input, inProgress));
                }
                else {
                    calcRecur(results, input, indexTracker, inProgress, depth+1);
                }

                indexTracker[index] = false;
                inProgress[depth] = null;
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
