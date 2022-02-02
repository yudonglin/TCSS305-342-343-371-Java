import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoubleLinkedListTest {
    /**
     * Test for the basic methods of {@link DoubleLinkedList}.
     */
    @Test
    public void testDoubleLinkedList() {
        var theList = new DoubleLinkedList();
        // tests for empty DoubleLinkedList
        Assertions.assertFalse(theList.containsCycle());
        Assertions.assertNull(theList.getHead());
        Assertions.assertNull(theList.getHead());
        Assertions.assertEquals(theList.size(), 0);
        // test for not empty DoubleLinkedList
        for (int i = 0; i < 10; i++) {
            theList.addRear(String.valueOf(i));
        }
        Assertions.assertFalse(theList.containsCycle());
        Assertions.assertEquals(theList.get(0), theList.getHead());
        Assertions.assertEquals(theList.get(theList.size() - 1), theList.getRear());
        theList.get(8).next = theList.get(1);
        Assertions.assertTrue(theList.containsCycle());
        // test for clear() method
        theList.clear();
        Assertions.assertEquals(theList.size(), 0);
        Assertions.assertNull(theList.getHead());
        Assertions.assertNull(theList.getHead());
        Assertions.assertFalse(theList.containsCycle());
    }

    /**
     * Test method for {@link DoubleLinkedList#toString()}.
     */
    @Test
    public void testDoubleLinkedListToString() {
        var theList = new DoubleLinkedList();
        Assertions.assertEquals(theList.toString(), "[]");
        theList.addRear("a");
        Assertions.assertEquals(theList.toString(), "[a]");
        theList.addRear("b");
        Assertions.assertEquals(theList.toString(), "[a->b]");
        theList.addRear("c");
        Assertions.assertEquals(theList.toString(), "[a->b->c]");
        theList.addFront("d");
        Assertions.assertEquals(theList.toString(), "[d->a->b->c]");
        Assertions.assertEquals(theList.size(), 4);
        theList.addRear("d");
        Assertions.assertEquals(theList.toString(), "[d->a->b->c->d]");
        theList.delete("d");
        Assertions.assertEquals(theList.toString(), "[a->b->c->d]");
        theList.delete("d");
        Assertions.assertEquals(theList.toString(), "[a->b->c]");
        theList.delete("b");
        Assertions.assertEquals(theList.toString(), "[a->c]");
        Assertions.assertEquals(theList.size(), 2);
        theList.clear();
        theList.delete("b");
        Assertions.assertEquals(theList.toString(), "[]");
        Assertions.assertEquals(theList.size(), 0);
        theList.addRear("a");
        theList.addRear("a");
        theList.addRear("b");
        theList.addRear("a");
        theList.addRear("c");
        theList.delete_all("b");
        theList.delete_all("c");
        theList.delete_all("a");
        Assertions.assertEquals(theList.toString(), "[]");
        Assertions.assertEquals(theList.size(), 0);
    }

    /**
     * Test method for {@link Stack}.
     */
    @Test
    public void testStack() {
        var theStack = new Stack();
        Assertions.assertEquals(theStack.size(), 0);
        Assertions.assertNull(theStack.peek());
        Assertions.assertNull(theStack.pop());
        theStack.push("1");
        Assertions.assertEquals(theStack.size(), 1);
        Assertions.assertEquals(theStack.peek(), "1");
        Assertions.assertEquals(theStack.pop(), "1");
        Assertions.assertEquals(theStack.size(), 0);
        theStack.push("1");
        theStack.push("2");
        Assertions.assertEquals(theStack.size(), 2);
        Assertions.assertEquals(theStack.peek(), "2");
        Assertions.assertEquals(theStack.pop(), "2");
        Assertions.assertEquals(theStack.size(), 1);
        Assertions.assertSame(theStack.getHead(), theStack.getRear());
        theStack.push("2");
        theStack.push("3");
        theStack.push("4");
        Assertions.assertEquals(theStack.size(), 4);
        Assertions.assertEquals(theStack.peek(), "4");
        theStack.pop();
        theStack.pop();
        theStack.pop();
        theStack.pop();
        Assertions.assertSame(theStack.getHead(), theStack.getRear());
        Assertions.assertNull(theStack.getRear());
        Assertions.assertEquals(theStack.size(), 0);
    }

    /**
     * Test method for {@link Stack}.
     */
    @Test
    public void testQueue() {
        var theQueue = new Queue();
        Assertions.assertEquals(theQueue.size(), 0);
        Assertions.assertNull(theQueue.peek());
        Assertions.assertNull(theQueue.pop());
        theQueue.push("1");
        Assertions.assertEquals(theQueue.size(), 1);
        Assertions.assertEquals(theQueue.peek(), "1");
        Assertions.assertEquals(theQueue.pop(), "1");
        Assertions.assertEquals(theQueue.size(), 0);
        theQueue.push("1");
        theQueue.push("2");
        Assertions.assertEquals(theQueue.size(), 2);
        Assertions.assertEquals(theQueue.peek(), "1");
        Assertions.assertEquals(theQueue.pop(), "1");
        Assertions.assertEquals(theQueue.size(), 1);
        Assertions.assertSame(theQueue.getHead(), theQueue.getRear());
        theQueue.push("2");
        theQueue.push("3");
        theQueue.push("4");
        Assertions.assertEquals(theQueue.size(), 4);
        Assertions.assertEquals(theQueue.peek(), "2");
        theQueue.pop();
        theQueue.pop();
        Assertions.assertEquals(theQueue.peek(), "3");
        theQueue.pop();
        theQueue.pop();
        Assertions.assertSame(theQueue.getHead(), theQueue.getRear());
        Assertions.assertNull(theQueue.getRear());
        Assertions.assertEquals(theQueue.size(), 0);
    }
}
