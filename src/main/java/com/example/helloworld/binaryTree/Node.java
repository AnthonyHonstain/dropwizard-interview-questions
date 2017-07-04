package com.example.helloworld.binaryTree;

/**
 * Created by Anthony Honstain on 7/4/17.
 */
public class Node {

    private int value;
    private Node lChild;
    private Node rChild;

    public Node(int value, Node lChild, Node rChild) {
        this.value = value;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public int getValue() {
        return value;
    }

    public Node getlChild() {
        return lChild;
    }

    public void setlChild(Node lChild) {
        this.lChild = lChild;
    }

    public Node getrChild() {
        return rChild;
    }

    public void setrChild(Node rChild) {
        this.rChild = rChild;
    }
}
