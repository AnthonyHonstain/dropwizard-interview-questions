package com.example.helloworld.lrucache;

/**
 * Created by Anthony Honstain on 1/28/17.
 */
public class PriorityNode {
    private PriorityNode leftNode;
    private PriorityNode rightNode;
    private String key;

    public PriorityNode(String key) {
        this.leftNode = null;
        this.rightNode = null;
        this.key = key;
    }

    public PriorityNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(PriorityNode leftNode) {
        this.leftNode = leftNode;
    }

    public PriorityNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(PriorityNode rightNode) {
        this.rightNode = rightNode;
    }

    public String getKey() {
        return key;
    }
}
