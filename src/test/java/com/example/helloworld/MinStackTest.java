package com.example.helloworld;

import com.example.helloworld.minStack.MinStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anthony Honstain on 7/1/17.
 */
public class MinStackTest {

    @Test
    public void minimumTest() {
        MinStack testStack = new MinStack();
        testStack.push(2);
        assertEquals(testStack.minimum(), new Integer(2));
        testStack.push(1);
        assertEquals(testStack.minimum(), new Integer(1));
    }

    @Test
    public void singlePushAndPopTest() {
        MinStack testStack = new MinStack();
        testStack.push(1);
        assertEquals(testStack.pop(), new Integer(1));
    }

    @Test
    public void multiplePushAndPopTest() {
        MinStack testStack = new MinStack();
        testStack.push(1);
        testStack.push(2);
        assertEquals(testStack.pop(), new Integer(2));
        assertEquals(testStack.pop(), new Integer(1));
    }

    @Test
    public void repeatedPushAndPopTest() {
        MinStack testStack = new MinStack();
        testStack.push(10);
        testStack.push(20);
        assertEquals(testStack.minimum(), new Integer(10));
        assertEquals(testStack.pop(), new Integer(20));
        assertEquals(testStack.minimum(), new Integer(10));

        testStack.push(5);
        assertEquals(testStack.minimum(), new Integer(5));
        testStack.push(30);
        assertEquals(testStack.minimum(), new Integer(5));
        assertEquals(testStack.pop(), new Integer(30));
        assertEquals(testStack.pop(), new Integer(5));
        assertEquals(testStack.minimum(), new Integer(10));
        assertEquals(testStack.pop(), new Integer(10));
    }
}
