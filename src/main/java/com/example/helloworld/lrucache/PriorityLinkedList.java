package com.example.helloworld.lrucache;

/**
 * Created by Anthony Honstain on 1/28/17.
 */
public class PriorityLinkedList {

    private int maxCapacity;
    private int size;

    private PriorityNode headNewest;
    private PriorityNode tailOldest;

    public PriorityLinkedList(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.size = 0;
    }

    public PriorityNode getHeadNewest() {
        return headNewest;
    }

    public int getSize() {
        return size;
    }

    public PriorityNode removeOldest() {
        assert(size == maxCapacity);

        PriorityNode prevTail = tailOldest;
        tailOldest = prevTail.getLeftNode();
        tailOldest.setRightNode(null);

        prevTail.setLeftNode(null);
        prevTail.setRightNode(null);
        size -= 1;
        return prevTail;
    }

    public void touch(PriorityNode priorityNode) {
        assert(size <= maxCapacity);

        if (size <= 1) {
            // No-op
        }
        else {

            if (priorityNode == headNewest) {
                // No-op
            }
            else if (priorityNode == tailOldest) {
                PriorityNode prevHead = headNewest;

                priorityNode.getLeftNode().setRightNode(null);
                tailOldest = priorityNode.getLeftNode();

                headNewest = priorityNode;
                headNewest.setLeftNode(null);
                headNewest.setRightNode(prevHead);
                prevHead.setLeftNode(headNewest);
            } else {
                PriorityNode prevHead = headNewest;

                priorityNode.getLeftNode().setRightNode(
                        priorityNode.getRightNode()
                );
                priorityNode.getRightNode().setLeftNode(
                        priorityNode.getLeftNode()
                );
                headNewest = priorityNode;
                headNewest.setLeftNode(null);
                headNewest.setRightNode(prevHead);
                prevHead.setLeftNode(headNewest);
            }
        }
    }

    public void insert(PriorityNode priorityNode) {
        assert(size < maxCapacity);

        if (size <= 0) {
            assert(headNewest == null && tailOldest == null);
            headNewest = priorityNode;
            tailOldest = priorityNode;
            priorityNode.setLeftNode(null);
            priorityNode.setRightNode(null);
        }
        else {
            PriorityNode prevHead = headNewest;
            headNewest = priorityNode;
            headNewest.setLeftNode(null);
            headNewest.setRightNode(prevHead);
            prevHead.setLeftNode(headNewest);
        }
        size += 1;
    }
}
