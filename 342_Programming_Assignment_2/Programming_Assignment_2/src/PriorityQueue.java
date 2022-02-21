public class PriorityQueue<E> extends ArrayHeap<E> {

    public PriorityQueue(int size) {
        super(size);
    }

    /**
     * takes element and priority value as parameters to create a Huffman Tree Node and add it to the Priority Queue based on the priority value.
     */
    public void addElement(E data, int priority) {
        this.add(new HuffmanTreeNode<>(data, priority));
    }

    /**
     * removes and returns the highest priority Huffman Tree Node from the Priority Queue.
     *
     * @return the highest priority Huffman Tree Node from the Priority Queue.
     */
    public HuffmanTreeNode<E> removeNext() {
        return this.removeMin();
    }
}
