package src;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListTest {

    /**
     * Test for the basic methods of {@link LinkedList}.
     */
    @Test
    public void testLinkedList() {
        var theList = new LinkedList();
        // tests for empty LinkedList
        Assertions.assertFalse(theList.containsCycle());
        Assertions.assertEquals(theList.getHead(), null);
        Assertions.assertEquals(theList.getHead(), null);
        Assertions.assertEquals(theList.size(), 0);
        // test for not empty LinkedList
        for (int i = 0; i < 10; i++) {
            theList.add((char) i);
        }
        Assertions.assertFalse(theList.containsCycle());
        Assertions.assertEquals(theList.get(0), theList.getHead());
        Assertions.assertEquals(theList.get(theList.size() - 1), theList.getRear());
        theList.get(8).next = theList.get(1);
        Assertions.assertTrue(theList.containsCycle());
        // test for clear() method
        theList.clear();
        Assertions.assertEquals(theList.size(), 0);
        Assertions.assertEquals(theList.getHead(), null);
        Assertions.assertEquals(theList.getHead(), null);
        Assertions.assertFalse(theList.containsCycle());
    }

    /**
     * Test method for {@link LinkedList#toString()}.
     */
    @Test
    public void testLinkedListToString() {
        var theList = new LinkedList();
        Assertions.assertEquals(theList.toString(), "[]");
        theList.add('a');
        Assertions.assertEquals(theList.toString(), "[a]");
        theList.add('b');
        Assertions.assertEquals(theList.toString(), "[a->b]");
        theList.add('c');
        Assertions.assertEquals(theList.toString(), "[a->b->c]");
    }

    /**
     * Test method for {@link LinkedList#deleteDuplicates()}.
     */
    @Test
    public void testLinkedListDeleteDuplicates() {
        var theList = new LinkedList();
        theList.add('a');
        theList.add('b');
        theList.add('c');
        theList.add('c');
        theList.add('a');
        theList.deleteDuplicates();
        Assertions.assertEquals(theList.toString(), "[a->b->c]");
        theList.clear();
        theList.deleteDuplicates();
        Assertions.assertEquals(theList.toString(), "[]");
        theList.add('a');
        theList.add('a');
        theList.deleteDuplicates();
        Assertions.assertEquals(theList.toString(), "[a]");
    }
}
