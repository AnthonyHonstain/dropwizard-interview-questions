package com.example.helloworld.lrucacheV2;

import java.util.HashMap;

public class LRUCacheV2Broken {

    private LLNode head;
    private LLNode tail;
    private HashMap<String, ValueNode> hashMap;
    private Integer capacity;

    public LRUCacheV2Broken(Integer capacity) {
        this.hashMap = new HashMap<>();
        this.capacity = capacity;
    }

    public LLNode exposeLinkedListForTesting() {
        return head;
    }

    public String get(String key) {
        ValueNode valueNode = hashMap.get(key);
        if (valueNode == null) {
            return null;
        }
        removeLLNode(valueNode.node);
        insertLLnode(valueNode.node);
        return valueNode.value;
    }

    public void set(String key, String value) {
        // There is nasty bug here, because we create the node without regard for the
        // linked list node that might already exist for this key, repeated insert can
        // result in extra nodes getting left in the linked list, but not in the cache.

        // ************************************************
        ValueNode valueNode = new ValueNode();
        valueNode.key = key;
        valueNode.value = value;
        valueNode.node = new LLNode();
        valueNode.node.valueNode = valueNode;
        // ************************************************

        if (hashMap.size() >= capacity) {
            LLNode old = tail;
            removeLLNode(old);
            hashMap.remove(old.valueNode.key);
        }
        hashMap.put(key, valueNode);
        insertLLnode(valueNode.node);
    }

    private void removeLLNode(LLNode removeNode) {
        if (head == null || removeNode == null) {
            throw new RuntimeException();
        }
        LLNode left = removeNode.left;
        LLNode right = removeNode.right;

        if (head == removeNode && tail == removeNode) {
            head = null;
            tail = null;
        }
        else if (head == removeNode) {
           right.left = null;
           head = right;
        }
        else if (tail == removeNode) {
            left.right = null;
            tail = left;
        }
        else {
            left.right = right;
            right.left = left;
        }
        removeNode.left = null;
        removeNode.right = null;
    }

    private void insertLLnode(LLNode addNode){
        addNode.right = head;
        if (head != null) {
            head.left = addNode;
        }
        if (tail == null) {
            tail = addNode;
        }
        head = addNode;
    }

    /**
     * The decision to use two separate objects (one for the hashMap value and
     * one for the linked list) resulted in a bunch of extra code, that actually
     * resulted in a serious bug. Maybe it would have made sense if I were
     * planning to pulled the responsibilities apart for a more realistic implementation
     * BUT certainly not helpful in the context of an interview problem.
     */
    public class ValueNode {
        public String key;
        public String value;
        public LLNode node;
    }

    public class LLNode {
        public LLNode left;
        public LLNode right;
        public ValueNode valueNode;
    }
}
