public class PriorityQueue<E> extends ArrayHeap<E> {


    public PriorityQueue(int size) {
        super(size);
    }

    public void addElement(E data, int priority) {
        this.add(new HuffmanTreeNode<>(data, priority));
    }

    public HuffmanTreeNode<E> removeNext() {
        return this.removeMin();
    }
}
