public class DoublyNode<E> {

    // data stored in this node
    private final E data;
    // link to next node in the list
    public DoublyNode<E> next;
    // link to previous node in the list
    public DoublyNode<E> prev;


    /**
     * constructs a node with given data and null next and prev
     *
     * @param data the data that is held by this node
     */
    public DoublyNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * @return the data that is held by this node
     */
    public E getData() {
        return data;
    }
}
