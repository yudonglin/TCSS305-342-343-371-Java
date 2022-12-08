/**
 * A binary minheap of edge objects.
 *
 * @author Donald Chinn
 * @author yudong lin
 * @version 1.1 - December 5, 2022
 */
public class BinaryHeap {

    /* the heap is organized using the implicit array implementation.
     * Array index 0 is not used
     */
    private Edge[] elements;
    private int size;       // index of last element in the heap

    // Constructor
    public BinaryHeap() {
        final int initialCapacity = 10;

        this.elements = new Edge[initialCapacity + 1];
        this.elements[0] = null;
        this.size = 0;
    }

    /**
     * compare two edges
     *
     * @param e1 the first edge
     * @param e2 the second edge
     * @return a number indicate whether fix edge costs more than second edge
     */
    private static int compare(final Edge e1, final Edge e2) {
        if ((double) e1.getData() < (double) e2.getData()) {
            return -1;
        } else if ((double) e1.getData() > (double) e2.getData()) {
            return 1;
        }
        return 0;
    }

    /**
     * Determine whether the heap is not empty.
     *
     * @return true if the heap is not empty; false otherwise
     */
    public boolean isNotEmpty() {
        return (size >= 1);
    }

    /**
     * Insert an object into the heap.
     *
     * @param key a key
     */
    public void insert(final Edge key) {

        if (size >= elements.length - 1) {
            // not enough room -- create a new array and copy
            // the elements of the old array to the new
            final Edge[] newElements = new Edge[2 * size];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }

        size++;
        elements[size] = key;
        percolateUp(size);
    }

    /**
     * Remove the object with minimum key from the heap.
     *
     * @return the object with minimum key of the heap
     */
    public Edge deleteMin() {
        if (isNotEmpty()) {
            final Edge returnValue = elements[1];
            elements[1] = elements[size];
            size--;
            percolateDown(1);
            return returnValue;

        }
        return null;
    }

    /**
     * Given an index in the heap array, percolate that key up the heap.
     *
     * @param index an index into the heap array
     */
    private void percolateUp(int index) {
        final Edge temp = elements[index];  // keep track of the item to be moved
        while (index > 1) {
            if (compare(temp, elements[index / 2]) < 0) {
                elements[index] = elements[index / 2];
                index = index / 2;
            } else {
                break;
            }
        }
        elements[index] = temp;
    }

    /**
     * Given an index in the heap array, percolate that key down the heap.
     *
     * @param index an index into the heap array
     */
    private void percolateDown(int index) {
        int child;
        final Edge temp = elements[index];

        while (2 * index <= size) {
            child = 2 * index;
            if ((child != size) &&
                    (compare(elements[child + 1], elements[child]) < 0)) {
                child++;
            }
            // ASSERT: at this point, elements[child] is the smaller of
            // the two children
            if (compare(elements[child], temp) < 0) {
                elements[index] = elements[child];
                index = child;
            } else {
                break;
            }
        }
        elements[index] = temp;

    }
}
