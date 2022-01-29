package src;

public class Node<E> {
    // link to next node in the list
    public Node<E> next;
    // data stored in this node
    private E data;

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
    public String getData() {
        return data.toString();
    }

    /**
     * set the data that is held by this node
     *
     * @param data the new data
     */
    public void setData(E data) {
        this.data = data;
    }
}
