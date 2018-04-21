package com.example.helloworld.binaryTree;

public class BinarySearchCheck {
    private int max;
    private boolean isBinarySearch;

    public BinarySearchCheck() {
        this.max = Integer.MIN_VALUE;
        this.isBinarySearch = true;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean isBinarySearch() {
        return isBinarySearch;
    }

    public void setBinarySearch(boolean binarySearch) {
        isBinarySearch = binarySearch;
    }
}
