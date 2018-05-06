package com.example.helloworld;

import com.example.helloworld.binaryTree.BinarySearchCheck;
import com.example.helloworld.binaryTree.BinaryTree;
import com.example.helloworld.binaryTree.Node;
import com.example.helloworld.binaryTree.UnbalancedNodeException;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anthony Honstain on 7/4/17.
 */
public class BinaryTreeTest {

    @Test
    public void getHeightBasic() {
        Node root = new Node(10, null, null);
        assertEquals(BinaryTree.getHeight(root), 1);
    }

    @Test
    public void getHeightMultipleBalanced() {
        Node root = new Node(10,
                new Node(5, null, null), new Node(15, null, null));
        assertEquals(BinaryTree.getHeight(root), 2);

        root.getrChild().setlChild(new Node(12, null, null));
        assertEquals(BinaryTree.getHeight(root), 3);
    }

    @Test(expected = UnbalancedNodeException.class)
    public void getHeightUnbalanced() {
        Node root = new Node(10,
                null, new Node(15, null, new Node(20, null, null)));
        BinaryTree.getHeight(root);
    }

    @Test
    public void checkBalancedBasic() {
        Node root = new Node(10, null, null);
        assertTrue(BinaryTree.checkBalanced(root));
    }

    @Test
    public void checkBalancedComplicated() {
        Node root = getBalancedBinaryTreeDepthTree();
        assertTrue(BinaryTree.checkBalanced(root));
    }

    @Test
    public void checkBalancedFalse() {
        Node root = getUnbalancedBinaryTree();
        assertFalse(BinaryTree.checkBalanced(root));
    }

    @Test
    public void checkTreeRecursive() {
        Node root = getBalancedBinaryTreeDepthTree();
        BinarySearchCheck binarySearchCheck = new BinarySearchCheck();
        BinaryTree.checkTreeRecursive(root, binarySearchCheck);
        assertTrue(binarySearchCheck.isBinarySearch());

        root = getUnbalancedBinaryTree();
        binarySearchCheck = new BinarySearchCheck();
        BinaryTree.checkTreeRecursive(root, binarySearchCheck);
        assertTrue(binarySearchCheck.isBinarySearch());
    }

    @Test
    public void checkTreeRecursiveNotBinarySearch() {
        /*
              10
             /  \
            15   20
         */
        Node root = new Node(10, null, null);
        root.setlChild(new Node(15, null, null));
        root.setrChild(new Node(20, null, null));
        BinarySearchCheck binarySearchCheck = new BinarySearchCheck();
        BinaryTree.checkTreeRecursive(root, binarySearchCheck);
        assertFalse(binarySearchCheck.isBinarySearch());
    }

    @Test
    public void recursiveBuildEmpty() {
        Node result = BinaryTree.recursiveBuild(ImmutableList.of(), 0, -1);
        assertNull(result);
    }

    @Test
    public void recursiveBuildSmall() {
        /*
             1
            / \
           0   2
         */
        Node result = BinaryTree.recursiveBuild(ImmutableList.of(0,1,2), 0, 2);
        assertEquals(1, result.getValue());
        assertEquals(0, result.getlChild().getValue());
        assertEquals(2, result.getrChild().getValue());
    }

    @Test
    public void recursiveBuildBookMedium() {
        /*
             1
            / \
           0   2
                \
                 3
         */
        Node result = BinaryTree.recursiveBuildBook(ImmutableList.of(0,1,2,3), 0, 3);
        assertEquals(1, result.getValue());
        assertEquals(0, result.getlChild().getValue());
        assertEquals(3, result.getrChild().getValue());
        assertEquals(2, result.getrChild().getlChild().getValue());

        result = BinaryTree.recursiveBuildBook(ImmutableList.of(0,1,2,3,4), 0, 4);
        assertEquals(2, result.getValue());
        assertEquals(1, result.getlChild().getValue());
        assertEquals(4, result.getrChild().getValue());

        result = BinaryTree.recursiveBuildBook(ImmutableList.of(0,1,2,3,4,5), 0, 5);
        assertEquals(2, result.getValue());

        assertEquals(1, result.getlChild().getValue());
        assertEquals(0, result.getlChild().getlChild().getValue());

        assertEquals(4, result.getrChild().getValue());
        assertEquals(3, result.getrChild().getlChild().getValue());
        assertEquals(5, result.getrChild().getrChild().getValue());

        assertTrue(BinaryTree.checkBalanced(result));
    }

    @Test
    public void recursiveBuildComplete() {
        /*
                  3
                /   \
               1     5
              / \   /  \
             0   2 4    6
         */
        Node result = BinaryTree.recursiveBuild(ImmutableList.of(0,1,2,3,4,5,6), 0, 6);
        assertEquals(3, result.getValue());
        assertEquals(1, result.getlChild().getValue());
        assertEquals(0, result.getlChild().getlChild().getValue());
        assertEquals(2, result.getlChild().getrChild().getValue());

        assertEquals(5, result.getrChild().getValue());
        assertEquals(4, result.getrChild().getlChild().getValue());
        assertEquals(6, result.getrChild().getrChild().getValue());
    }

    @Test
    public void recursiveBuildLarge() {
        Node result = BinaryTree.recursiveBuild(ImmutableList.of(0,1,2,3,4,5,6,7), 0, 7);
        assertEquals(4, result.getValue());
        assertEquals(2, result.getlChild().getValue());
        assertEquals(1, result.getlChild().getlChild().getValue());
        assertEquals(0, result.getlChild().getlChild().getlChild().getValue());
        assertEquals(6, result.getrChild().getValue());

        assertTrue(BinaryTree.checkBalanced(result));
    }

    @Test
    public void recursiveBuildDepthFive() {
        /*
                   ---9---
                  /        \
                 4          14
                / \         /  \
               2   7       12    16
              / \  | \    /  \   / \
             1  3  6  8  11  13 15  17
            /      /     /
           0      5     10

         */
        Node result = BinaryTree.recursiveBuild(
                ImmutableList.of(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17), 0, 17);
        assertEquals(9, result.getValue());
        assertEquals(4, result.getlChild().getValue());
        assertEquals(2, result.getlChild().getlChild().getValue());
        assertEquals(14, result.getrChild().getValue());

        assertTrue(BinaryTree.checkBalanced(result));
    }

    @Test
    public void constructFromSortedArray() {
        Node result = BinaryTree.constructFromSortedArray(ImmutableList.of());
        assertNull(result);

        result = BinaryTree.constructFromSortedArray(ImmutableList.of(0));
        assertEquals(0, result.getValue());

        result = BinaryTree.constructFromSortedArray(ImmutableList.of(0,1));
        assertEquals(1, result.getValue());
        assertEquals(0, result.getlChild().getValue());

        result = BinaryTree.constructFromSortedArray(ImmutableList.of(0,1,2));
        assertEquals(1, result.getValue());
        assertEquals(0, result.getlChild().getValue());
        assertEquals(2, result.getrChild().getValue());
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
