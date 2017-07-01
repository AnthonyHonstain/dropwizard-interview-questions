package com.example.helloworld.minStack;

import java.util.ArrayList;

/**
 * Created by Anthony Honstain on 7/1/17.
 */
public class MinStack {
    private ArrayList<Integer> userElements = new ArrayList<>();
    private ArrayList<Integer> minElements = new ArrayList<>();

    public Integer minimum() {
        if (userElements.size() == 0) {
            throw new RuntimeException();
        }
        Integer indexOfMin = minElements.get(minElements.size() - 1);
        return userElements.get(indexOfMin);
    }

    public void push(Integer newElement) {
        if (userElements.size() == 0) {
            userElements.add(0, newElement);
            minElements.add(0, 0);
            return;
        }

        Integer newUserElementIndex = userElements.size();

        if (newElement < minimum()) {
            minElements.add(minElements.size(), newUserElementIndex);
        }
        userElements.add(newUserElementIndex, newElement);
    }

    public Integer pop() {
        if (userElements.size() == 0) {
            throw new RuntimeException();
        }

        Integer result = userElements.get(userElements.size() - 1);

        if (userElements.size() - 1 == minElements.get(minElements.size() - 1)) {
            minElements.remove(minElements.size() - 1);
            minElements.trimToSize();
        }
        userElements.remove(userElements.size() - 1);
        // Note - I don't feel great about using trimToSize, unsure of perf cost
        // but it is vitally important to how I use the two ArrayLists.
        userElements.trimToSize();
        return result;
    }
}
