class HuffmanTreeNode<E> {
    // data stored in this node
    private final E data;
    private final int priority;
    public HuffmanTreeNode<E> left;
    public HuffmanTreeNode<E> right;

    /**
     * constructs a node with given data and null next
     *
     * @param data the data that is held by this node
     */
    public HuffmanTreeNode(E data, int priority) {
        this.data = data;
        this.priority = priority;
        this.left = null;
        this.right = null;
    }

    /**
     * @return the data that is held by this node
     */
    public E getData() {
        return data;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public String toString() {
        return "< data=" + data + ", priority=" + priority + " >";
    }

    public int compareTo(HuffmanTreeNode<E> o) {
        return this.getPriority() > o.getPriority() ? 1 : -1;
    }
}
