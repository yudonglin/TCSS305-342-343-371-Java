public class ArrayHeap<E> {

    private final HuffmanTreeNode<E>[] dataArray;
    private int count = 1;

    public ArrayHeap(int _max_size) {
        if (_max_size > 0) {
            this.dataArray = new HuffmanTreeNode[_max_size];
        } else {
            throw new NegativeArraySizeException("You cannot create a ArrayHeap with negative size!");
        }

    }

    private void swap(int nodeIndex1, int nodeIndex2) {
        var _data = dataArray[nodeIndex1];
        dataArray[nodeIndex1] = dataArray[nodeIndex2];
        dataArray[nodeIndex2] = _data;
    }

    public void add(HuffmanTreeNode<E> newNode) {
        dataArray[count] = newNode;
        int parentIndex;
        var currentNodeIndex = count;
        while (currentNodeIndex > 1) {
            parentIndex = currentNodeIndex / 2;
            if (dataArray[parentIndex].getPriority() <= newNode.getPriority()) {
                break;
            } else {
                this.swap(currentNodeIndex, parentIndex);
                // System.out.println("swap " + currentNodeIndex + " and "+parentIndex);
                currentNodeIndex = parentIndex;
            }
        }
        count++;
    }

    public HuffmanTreeNode<E> removeMin() {
        if (count > 1) {
            var _data = dataArray[1];
            count--;
            dataArray[1] = dataArray[count];
            dataArray[count] = null;
            int currentIndex = 1;
            int leftChildIndex, rightChildIndex;
            while (currentIndex < count) {
                leftChildIndex = 2 * currentIndex;
                rightChildIndex = leftChildIndex + 1;
                // if left and right child both exist (according to count, and note that right index is left index+1)
                if (rightChildIndex < count) {
                    if (this.dataArray[currentIndex].compareTo(this.dataArray[leftChildIndex]) > 0 && this.dataArray[currentIndex].compareTo(this.dataArray[rightChildIndex]) > 0) {
                        if (this.dataArray[leftChildIndex].getPriority() < this.dataArray[rightChildIndex].getPriority()) {
                            this.swap(leftChildIndex, currentIndex);
                            currentIndex = leftChildIndex;
                        } else {
                            this.swap(rightChildIndex, currentIndex);
                            currentIndex = rightChildIndex;
                        }
                    } else {
                        break;
                    }
                } else if (leftChildIndex < count && this.dataArray[currentIndex].compareTo(this.dataArray[leftChildIndex]) > 0) {
                    this.swap(leftChildIndex, currentIndex);
                    currentIndex = leftChildIndex;
                } else {
                    // we reach the leaf (or when there is an only bigger left)! Done!
                    break;
                }
            }
            return _data;
        } else {
            throw new IndexOutOfBoundsException("The Heap is empty!");
        }
    }

    @Override
    public String toString() {
        var _data = new StringBuilder();
        _data.append("[");
        for (int i = 0; i < count; i++) {
            _data.append(this.dataArray[i] != null ? this.dataArray[i].toString() : "null");
            if (i < count - 1) {
                _data.append(", ");
            }
        }
        _data.append("]");
        return _data.toString();
    }

    public boolean isEmpty() {
        return count <= 1;
    }

    public int size() {
        return count - 1;
    }

    public HuffmanTreeNode<E> peek() {
        return isEmpty() ? null : dataArray[1];
    }
}
