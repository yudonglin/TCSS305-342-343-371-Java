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
        Assertions.assertNull(theList.getHead());
        Assertions.assertNull(theList.getHead());
        Assertions.assertEquals(theList.size(), 0);
        // test for not empty LinkedList
        for (int i = 0; i < 10; i++) {
            theList.add(String.valueOf(i));
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
     * Test for the basic methods of {@link Node}.
     */
    @Test
    public void testNode() {
        var temp = new Node("a");
        Assertions.assertEquals(temp.getData(), "a");
        temp.setData("12221");
        Assertions.assertEquals(temp.getData(), "12221");
    }

    /**
     * Test method for {@link LinkedList#toString()}.
     */
    @Test
    public void testLinkedListToString() {
        var theList = new LinkedList();
        Assertions.assertEquals(theList.toString(), "[]");
        theList.add("a");
        Assertions.assertEquals(theList.toString(), "[a]");
        theList.add("b");
        Assertions.assertEquals(theList.toString(), "[a->b]");
        theList.add("c");
        Assertions.assertEquals(theList.toString(), "[a->b->c]");
    }

    /**
     * Test method for {@link LinkedList#swap(int i, int j)}.
     */
    @Test
    public void testLinkedListSwap() {
        var theList = new LinkedList();
        theList.add("a");
        theList.add("b");
        theList.add("c");
        theList.add("d");
        Assertions.assertEquals(theList.toString(), "[a->b->c->d]");
        theList.swap(2,3);
    }

    /**
     * Test method for {@link LinkedList#deleteDuplicates()}.
     */
    @Test
    public void testLinkedListDeleteDuplicates() {
        var theList = new LinkedList();
        theList.add("a");
        theList.add("b");
        theList.add("c");
        theList.add("c");
        theList.add("a");
        theList.deleteDuplicates();
        Assertions.assertEquals(theList.toString(), "[a->b->c]");
        theList.clear();
        theList.deleteDuplicates();
        Assertions.assertEquals(theList.toString(), "[]");
        theList.add("a");
        theList.add("a");
        theList.deleteDuplicates();
        Assertions.assertEquals(theList.toString(), "[a]");
    }

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
            theList.add_rear(String.valueOf(i));
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
        theList.add_rear("a");
        Assertions.assertEquals(theList.toString(), "[a]");
        theList.add_rear("b");
        Assertions.assertEquals(theList.toString(), "[a->b]");
        theList.add_rear("c");
        Assertions.assertEquals(theList.toString(), "[a->b->c]");
        theList.add_front("d");
        Assertions.assertEquals(theList.toString(), "[d->a->b->c]");
        Assertions.assertEquals(theList.size(), 4);
        theList.add_rear("d");
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
        theList.add_rear("a");
        theList.add_rear("a");
        theList.add_rear("b");
        theList.add_rear("a");
        theList.add_rear("c");
        theList.delete_all("b");
        theList.delete_all("c");
        theList.delete_all("a");
        Assertions.assertEquals(theList.toString(), "[]");
        Assertions.assertEquals(theList.size(), 0);
    }
}
