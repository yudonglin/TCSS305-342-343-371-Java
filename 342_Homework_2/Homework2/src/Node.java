public class Node<E> {
    // data stored in this node
    private final E data;
    // link to next node in the list
    public Node<E> next;

    /**
     * constructs a node with given data and null next
     *
     * @param data the data that is held by this node
     */
    public Node(E data) {
        this.data = data;
        this.next = null;
    }

    /**
     * @return the data that is held by this node
     */
    public E getData() {
        return data;
    }
}
