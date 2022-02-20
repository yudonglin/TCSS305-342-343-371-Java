class Node<E> {
    // data stored in this node
    private final E data;
    private final int priority;

    /**
     * constructs a node with given data and null next
     *
     * @param data the data that is held by this node
     */
    public Node(E data, int priority) {
        this.data = data;
        this.priority = priority;
    }

    /**
     * @return the data that is held by this node
     */
    public String getData() {
        return data.toString();
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "< data=" + data + ", priority=" + priority + " >";
    }
}

public class HuffmanTreeNode<E> extends Node<E> {
    /**
     * constructs a node with given data and null next
     *
     * @param data     the data that is held by this node
     * @param priority
     */
    public HuffmanTreeNode(E data, int priority) {
        super(data, priority);
    }
}
