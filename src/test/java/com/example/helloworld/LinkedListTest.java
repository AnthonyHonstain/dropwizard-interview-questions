package com.example.helloworld;

import com.example.helloworld.lrucache.PriorityLinkedList;
import com.example.helloworld.lrucache.PriorityNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Anthony Honstain on 1/28/17.
 */
public class LinkedListTest {

    private PriorityNode node1;
    private PriorityNode node2;
    private PriorityNode node3;

    @Before
    public void setUp() {
        node1 = new PriorityNode("node1");
        node2 = new PriorityNode("node2");
        node3 = new PriorityNode("node3");
    }

    @Test
    public void setFirst() {
        PriorityLinkedList pll = new PriorityLinkedList(4);
        pll.insert(node1);

        assertEquals(null, node1.getLeftNode());
        assertEquals(null, node1.getRightNode());
    }

    @Test
    public void setTwo() {
        PriorityLinkedList pll = new PriorityLinkedList(4);
        pll.insert(node1);
        pll.insert(node2);

        assertEquals(null, node2.getLeftNode());
        assertEquals(node1, node2.getRightNode());
        assertEquals(node2, node1.getLeftNode());
        assertEquals(null, node1.getRightNode());
    }

    @Test
    public void setRepeated() {
        PriorityLinkedList pll = new PriorityLinkedList(4);
        pll.insert(node1);
        pll.insert(node1);
        // NOTHING PREVENTS A CYCLE, weakness in the design.
        assertEquals(node1, node1.getLeftNode());
        assertEquals(node1, node1.getRightNode());
        assertEquals("node1", node1.getKey());
    }

    @Test(expected = AssertionError.class)
    public void setCornerCase() {
        PriorityLinkedList pll = new PriorityLinkedList(1);
        pll.insert(node1);
        pll.insert(node2);
    }

    @Test(expected = AssertionError.class)
    public void setToMany() {
        PriorityLinkedList pll = new PriorityLinkedList(2);
        pll.insert(node1);
        pll.insert(node2);
        pll.insert(node3);
    }

    @Test
    public void setMultiple() {
        PriorityLinkedList pll = new PriorityLinkedList(4);
        pll.insert(node1);
        pll.insert(node2);
        pll.insert(node3);

        assertEquals(null, node3.getLeftNode());
        assertEquals(node2, node3.getRightNode());
        assertEquals(node3, node2.getLeftNode());
        assertEquals(node1, node2.getRightNode());
        assertEquals(node2, node1.getLeftNode());
        assertEquals(null, node1.getRightNode());
    }

    @Test
    public void simpleTouch() {
        PriorityLinkedList pll = new PriorityLinkedList(4);
        pll.insert(node1);
        pll.insert(node2);
        pll.insert(node3);
        pll.touch(node2);

        assertEquals(null, node2.getLeftNode());
        assertEquals(node3, node2.getRightNode());
        assertEquals(node2, node3.getLeftNode());
        assertEquals(node1, node3.getRightNode());
        assertEquals(node3, node1.getLeftNode());
        assertEquals(null, node1.getRightNode());
    }

    @Test
    public void tailTouch() {
        PriorityLinkedList pll = new PriorityLinkedList(4);
        pll.insert(node1);
        pll.insert(node2);
        pll.insert(node3);
        pll.touch(node1);

        assertEquals(null, node1.getLeftNode());
        assertEquals(node3, node1.getRightNode());
        assertEquals(node1, node3.getLeftNode());
        assertEquals(node2, node3.getRightNode());
        assertEquals(node3, node2.getLeftNode());
        assertEquals(null, node2.getRightNode());
    }

    @Test
    public void removeBasic() {
        PriorityLinkedList pll = new PriorityLinkedList(3);
        pll.insert(node1);
        pll.insert(node2);
        pll.insert(node3);
        pll.removeOldest();

        assertEquals(null, node3.getLeftNode());
        assertEquals(node2, node3.getRightNode());
        assertEquals(node3, node2.getLeftNode());
        assertEquals(null, node2.getRightNode());
    }

    @Test
    public void removeAndInsert() {
        PriorityLinkedList pll = new PriorityLinkedList(3);
        pll.insert(node1);
        pll.insert(node2);
        pll.insert(node3);
        pll.removeOldest();
        pll.insert(node1);

        assertEquals(null, node1.getLeftNode());
        assertEquals(node3, node1.getRightNode());
        assertEquals(node1, node3.getLeftNode());
        assertEquals(node2, node3.getRightNode());
        assertEquals(node3, node2.getLeftNode());
        assertEquals(null, node2.getRightNode());
    }

    @Test(expected = AssertionError.class)
    public void removeRepeated() {
        PriorityLinkedList pll = new PriorityLinkedList(3);
        pll.insert(node1);
        pll.insert(node2);
        pll.insert(node3);
        pll.removeOldest();
        pll.removeOldest();
    }
}
