package com.example.helloworld;

import com.example.helloworld.binaryTree.BinaryTree;
import com.example.helloworld.binaryTree.Node;
import com.example.helloworld.binaryTree.UnbalancedNodeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Anthony Honstain on 7/4/17.
 */
public class BinaryTreeTest {

    @Test
    public void getHeightBasicTest() {
        Node root = new Node(10, null, null);
        assertEquals(BinaryTree.getHeight(root), 1);
    }

    @Test
    public void getHeightMultipleBalancedTest() {
        Node root = new Node(10,
                new Node(5, null, null), new Node(15, null, null));
        assertEquals(BinaryTree.getHeight(root), 2);

        root.getrChild().setlChild(new Node(12, null, null));
        assertEquals(BinaryTree.getHeight(root), 3);
    }

    @Test(expected = UnbalancedNodeException.class)
    public void getHeightUnbalancedTest() {
        Node root = new Node(10,
                null, new Node(15, null, new Node(20, null, null)));
        BinaryTree.getHeight(root);
    }

    @Test
    public void checkBalancedBasicTest() {
        Node root = new Node(10, null, null);
        assertTrue(BinaryTree.checkBalanced(root));
    }

    @Test
    public void checkBalancedComplicatedTest() {
        Node root = getBalancedBinaryTreeDepthTree();
        assertTrue(BinaryTree.checkBalanced(root));
    }

    @Test
    public void checkBalancedFalseTest() {
        Node root = getUnbalancedBinaryTree();
        assertFalse(BinaryTree.checkBalanced(root));
    }

    private Node getBalancedBinaryTreeDepthTree() {
    /*
               10
             /    \
            5      15
             \     /
              8   12
     */
        Node root = new Node(10, null, null);
        root.setlChild(new Node(5, null, new Node(8, null, null)));
        root.setrChild(new Node(15, new Node(12, null, null), null));
        return root;
    }

    private Node getUnbalancedBinaryTree() {
    /*
               10
             /    \
            5      15
                   /
                  12
                   \
                    13
     */
        Node root = new Node(10, null, null);
        root.setlChild(new Node(5, null, null));
        root.setrChild(new Node(15, new Node(12, null, new Node(13, null, null)), null));
        return root;
    }
}
