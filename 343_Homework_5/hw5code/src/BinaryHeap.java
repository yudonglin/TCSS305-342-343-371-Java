/**
 * A binary minheap of edge objects.
 *
 * @author Donald Chinn
 * @version September 19, 2003
 */
public class BinaryHeap {

    /* the heap is organized using the implicit array implementation.
     * Array index 0 is not used
     */
    private Edge[] elements;
    private int size;       // index of last element in the heap

    // Constructor
    public BinaryHeap() {
        int initialCapacity = 10;

        this.elements = new Edge[initialCapacity + 1];
        this.elements[0] = null;
        this.size = 0;
    }


    /**
     * Constructor
     *
     * @param capacity number of active elements the heap can contain
     */
    public BinaryHeap(int capacity) {
        this.elements = new Edge[capacity + 1];
        this.elements[0] = null;
        this.size = 0;
    }


    /**
     * Given an array of Edges, return a binary heap of those
     * elements.
     *
     * @param data an array of data (no particular order)
     * @return a binary heap of the given data
     */
    public static BinaryHeap buildHeap(Edge[] data) {
        BinaryHeap newHeap = new BinaryHeap(data.length);
        System.arraycopy(data, 0, newHeap.elements, 1, data.length);
        newHeap.size = data.length;
        for (int i = newHeap.size / 2; i > 0; i--) {
            newHeap.percolateDown(i);
        }
        return newHeap;
    }

    private static int compare(Edge e1, Edge e2) {
        if ((int) e1.getData() < (int) e2.getData()) {
            return -1;
        } else if ((int) e1.getData() > (int) e2.getData()) {
            return 1;
        }
        return 0;
    }

    /**
     * Determine whether the heap is empty.
     *
     * @return true if the heap is empty; false otherwise
     */
    public boolean isEmpty() {
        return (size < 1);
    }

    /**
     * Insert an object into the heap.
     *
     * @param key a key
     */
    public void insert(Edge key) {

        if (size >= elements.length - 1) {
            // not enough room -- create a new array and copy
            // the elements of the old array to the new
            Edge[] newElements = new Edge[2 * size];
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
        if (!isEmpty()) {
            Edge returnValue = elements[1];
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
        Edge temp = elements[index];  // keep track of the item to be moved
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
        Edge temp = elements[index];

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
