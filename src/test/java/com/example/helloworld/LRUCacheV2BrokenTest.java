package com.example.helloworld;

import com.example.helloworld.lrucacheV2.LRUCacheV2Broken;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LRUCacheV2BrokenTest {

    @Test
    public void empty() {
        LRUCacheV2Broken lruCache = new LRUCacheV2Broken(2);
        assertEquals(null, lruCache.get("foo"));
    }

    @Test
    public void emptySetAndGet() {
        LRUCacheV2Broken lruCache = new LRUCacheV2Broken(2);
        lruCache.set("foo", "1");
        assertEquals("1", lruCache.get("foo"));
    }

    @Test
    public void emptySetAndGetUpdate() {
        LRUCacheV2Broken lruCache = new LRUCacheV2Broken(2);
        lruCache.set("foo", "1");
        assertEquals("1", lruCache.get("foo"));
        lruCache.set("foo", "2");
        assertEquals("2", lruCache.get("foo"));
    }

    @Test
    public void smallEviction() {
        LRUCacheV2Broken lruCache = new LRUCacheV2Broken(2);
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
    public void repeatedSet() {
        LRUCacheV2Broken lruCache = new LRUCacheV2Broken(4);
        lruCache.set("foo", "1");
        lruCache.set("bar", "2");
        lruCache.set("foo", "3");
        lruCache.set("foo", "4");

        LRUCacheV2Broken.LLNode current = lruCache.exposeLinkedListForTesting();
        assertEquals("4", current.valueNode.value);
        current = current.right;

        assertEquals("3", current.valueNode.value);
        current = current.right;

        assertEquals("2", current.valueNode.value);
        current = current.right;

        assertEquals("1", current.valueNode.value);
        assertNull(current.right);
    }
}
