package com.example.helloworld.binaryTree;

import java.util.List;

/**
 * Created by Anthony Honstain on 7/4/17.
 */
public class BinaryTree {

    public static int getHeight(Node node) {
        int leftHeight = 0;
        int rightHeight = 0;

        if (node.getlChild() != null) {
            leftHeight = getHeight(node.getlChild());
        }
        if (node.getrChild() != null) {
            rightHeight = getHeight(node.getrChild());
        }

        if (Math.abs(leftHeight - rightHeight) > 1){
            throw new UnbalancedNodeException();
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static boolean checkBalanced(Node root) {
        try {
            getHeight(root);
        }
        catch (UnbalancedNodeException e) {
            return false;
        }
        return true;
    }

    public static void checkTreeRecursive(Node node, BinarySearchCheck binaryCheck) {
        if (node == null) {
            return;
        }
        checkTreeRecursive(node.getlChild(), binaryCheck);
        if (node.getValue() < binaryCheck.getMax()) {
            binaryCheck.setBinarySearch(false);
        }
        else {
            binaryCheck.setMax(node.getValue());
        }
        checkTreeRecursive(node.getrChild(), binaryCheck);
    }

    public static Node constructFromSortedArray(List<Integer> array) {
        Node root = recursiveBuild(array, 0, array.size() - 1);
        return root;
    }

    public static Node recursiveBuild(List<Integer> array, int start, int end) {
        int length = end - start + 1;
        if (length <= 0) { return null; }
        if (length == 1) { return new Node(array.get(start), null, null); }

        int mid = Math.floorDiv(length, 2) + start;
        Node newNode = new Node(
                array.get(mid),
                recursiveBuild(array, start, mid - 1),
                recursiveBuild(array, mid + 1, end)
        );
        return newNode;
    }

    public static Node recursiveBuildBook(List<Integer> array, int start, int end) {
        if (end < start) { return null; }

        int mid = Math.floorDiv(start + end, 2);
        Node newNode = new Node(
                array.get(mid),
                recursiveBuild(array, start, mid - 1),
                recursiveBuild(array, mid + 1, end)
        );
        return newNode;
    }
}