package com.example.helloworld.binaryTree;

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
}