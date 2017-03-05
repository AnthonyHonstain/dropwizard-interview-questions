package com.example.helloworld.lrucache;

import java.util.HashMap;

/**
 * Created by Anthony Honstain on 1/28/17.
 */
public class LRUCache {

    private int maxCapacity;
    private int size;

    private HashMap<String, CacheValue> cache;
    private PriorityLinkedList priorityLinkedList;

    public LRUCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        size = 0;

        cache = new HashMap<>();
        priorityLinkedList = new PriorityLinkedList(maxCapacity);
    }

    public LRUCache(int maxCapacity, PriorityLinkedList priorityLinkedList) {
        this.maxCapacity = maxCapacity;
        size = 0;

        cache = new HashMap<>();
        this.priorityLinkedList = priorityLinkedList;
    }

    public String get(String key) {
        CacheValue cacheValue = cache.get(key);

        if (cacheValue == null) {
            return null;
        }
        String value = cacheValue.value;
        priorityLinkedList.touch(cacheValue.priorityNode);
        return value;
    }

    public void set(String key, String value) {
        if (value == null) {
            throw new RuntimeException("null value is not supported");
        }

        CacheValue cacheValue = cache.get(key);
        if (cacheValue != null) {
            cacheValue.value = value;
            return;
        }

        if (size == maxCapacity) {
            evict();
            addKeyAndUpdatePriority(key, value);
        }
        else {
            size += 1;
            addKeyAndUpdatePriority(key, value);
        }
    }

    private void evict() {
        PriorityNode oldestNode = priorityLinkedList.removeOldest();
        String oldestKey = oldestNode.getKey();
        cache.remove(oldestKey);
    }

    private void addKeyAndUpdatePriority(String key, String value){
        PriorityNode priorityNode = new PriorityNode(key);
        CacheValue cacheValue = new CacheValue(value, priorityNode);
        cache.put(key, cacheValue);
        priorityLinkedList.insert(priorityNode);

        assert(size == cache.size());
        assert(size == priorityLinkedList.getSize());
    }
    
    public class CacheValue {
        private String value;
        private PriorityNode priorityNode;

        public CacheValue(String value, PriorityNode priorityNode) {
            this.value = value;
            this.priorityNode = priorityNode;
        }
    }
}

