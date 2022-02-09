import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HW2Test {

    // Binary to Decimal Tests

    /**
     * Test method for {@link MinStack}.
     */
    @Test
    public void testMinStack() {
        var minS = new MinStack();
        minS.push(5);
        minS.push(2);
        minS.push(1);
        minS.push(3);
        minS.push(0);

        assertEquals(minS.getMin(), 0);
        assertEquals(minS.peek(), 0);
        assertEquals(minS.pop(), 0);

        assertEquals(minS.getMin(), 1);
        assertEquals(minS.peek(), 3);
        assertEquals(minS.pop(), 3);

        assertEquals(minS.getMin(), 1);
        minS.push(-5);
        assertEquals(minS.getMin(), -5);

        minS.push(3);
        assertEquals(minS.getMin(), -5);
        assertEquals(minS.peek(), 3);
        assertEquals(minS.pop(), 3);

        minS.push(-10);
        assertEquals(minS.getMin(), -10);
        assertEquals(minS.peek(), -10);
        assertEquals(minS.pop(), -10);

    }

    /**
     * Test method for {@link DummyNodeQueue}.
     */
    @Test
    public void testDummyNodeQueue() {
        var DNQueue = new DummyNodeQueue<Integer>();
        assertSame(DNQueue.getHead(), DNQueue.getDummy());
        assertSame(DNQueue.getRear(), DNQueue.getDummy());
        DNQueue.enqueue(1);
        assertNotSame(DNQueue.getHead(), DNQueue.getDummy());
        assertNotSame(DNQueue.getRear(), DNQueue.getDummy());
        assertSame(DNQueue.getRear(), DNQueue.getHead());
        DNQueue.enqueue(2);
        assertNotSame(DNQueue.getRear(), DNQueue.getHead());
        assertSame(DNQueue.getDummy().next, DNQueue.getHead());

        assertEquals(DNQueue.dequeue(), 1);
        assertNotSame(DNQueue.getHead(), DNQueue.getDummy());
        assertNotSame(DNQueue.getRear(), DNQueue.getDummy());
        assertSame(DNQueue.getRear(), DNQueue.getHead());

        assertEquals(DNQueue.dequeue(), 2);
        assertSame(DNQueue.getHead(), DNQueue.getDummy());
        assertSame(DNQueue.getRear(), DNQueue.getDummy());
    }

    /**
     * Test method for {@link DqueueArray}.
     */
    @Test
    public void testDqueueArray() {
        var dqueue = new DqueueArray<Integer>();

        dqueue.enqueueRear(2);
        dqueue.enqueueRear(5);

        assertEquals(dqueue.get(0), 2);
        assertEquals(dqueue.get(1), 5);

        assertEquals(dqueue.dequeueFront(), 2);
        assertNull(dqueue.get(0));

        dqueue.enqueueRear(7);
        assertEquals(dqueue.get(2), 7);

        dqueue.enqueueFront(4);
        assertEquals(dqueue.get(0), 4);
        dqueue.enqueueFront(10);
        assertEquals(dqueue.get(9), 10);
        assertEquals(dqueue.dequeueFront(), 10);
        assertEquals(dqueue.dequeueFront(), 4);
        assertEquals(dqueue.dequeueFront(), 5);
        assertEquals(dqueue.dequeueRear(), 7);
    }

    /**
     * Test method for {@link DqueueLinkedList}.
     */
    @Test
    public void testDqueueLinkedList() {
        var dqueue = new DqueueLinkedList<Integer>();

        dqueue.enqueueRear(2);
        dqueue.enqueueRear(5);

        assertEquals(dqueue.dequeueFront(), 2);

        dqueue.enqueueRear(7);

        dqueue.enqueueFront(4);
        dqueue.enqueueFront(10);
        assertEquals(dqueue.dequeueFront(), 10);
        assertEquals(dqueue.dequeueFront(), 4);
        assertEquals(dqueue.dequeueFront(), 5);
        assertEquals(dqueue.dequeueRear(), 7);
    }
}
