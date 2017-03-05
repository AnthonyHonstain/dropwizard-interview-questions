package com.example.helloworld;

import com.example.helloworld.lrucache.LRUCache;
import com.example.helloworld.lrucache.PriorityLinkedList;
import com.example.helloworld.lrucache.PriorityNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anthony Honstain on 1/28/17.
 */
public class LRUCacheTest {

    @Test
    public void empty() {
        LRUCache lruCache = new LRUCache(2);
        assertEquals(null, lruCache.get("foo"));
    }

    @Test
    public void emptySetAndGet() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.set("foo", "1");
        assertEquals("1", lruCache.get("foo"));
    }

    @Test
    public void emptySetAndGetUpdate() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.set("foo", "1");
        assertEquals("1", lruCache.get("foo"));
        lruCache.set("foo", "2");
        assertEquals("2", lruCache.get("foo"));
    }

    @Test
    public void smallEviction() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.set("foo", "1");
        lruCache.set("bar", "2");
        lruCache.set("car", "3");

        assertEquals("2", lruCache.get("bar"));
        assertEquals("3", lruCache.get("car"));
        assertEquals(null, lruCache.get("foo"));

        lruCache.set("foo", "1");
        assertEquals(null, lruCache.get("bar"));
        assertEquals("3", lruCache.get("car"));
        assertEquals("1", lruCache.get("foo"));
    }

    @Test
    public void mixSetAnGet() {
        PriorityLinkedList priorityLinkedList = new PriorityLinkedList(10);
        LRUCache lruCache = new LRUCache(10, priorityLinkedList);
        lruCache.set("foo", "1");
        lruCache.set("bar", "2");
        lruCache.set("car", "3");
        assertEquals("2", lruCache.get("bar"));
        assertEquals("3", lruCache.get("car"));
        lruCache.set("dar", "4");

        // Going to walk the Priority LL and see if its in the expected order.
        PriorityNode node = priorityLinkedList.getHeadNewest();
        assertEquals("dar", node.getKey());
        node = node.getRightNode();
        assertEquals("car", node.getKey());
        node = node.getRightNode();
        assertEquals("bar", node.getKey());
        node = node.getRightNode();
        assertEquals("foo", node.getKey());
        assertEquals(null, node.getRightNode());
    }
}
