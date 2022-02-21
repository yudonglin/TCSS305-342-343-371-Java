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

    /**
     * takes  Huffman  Tree  Node  as  a  parameter,  adds  it  to  the ArrayHeap and reorders the heap (heapify).
     *
     * @param newNode the new Node
     */
    public void add(HuffmanTreeNode<E> newNode) {
        // add the new Node to the most right
        dataArray[count] = newNode;
        // re-order the heap (Heapify from bottom to top)
        int parentIndex;
        var currentNodeIndex = count;
        while (currentNodeIndex > 1) {
            parentIndex = currentNodeIndex / 2;
            // if parent is greater than current new Node, then no swap is needed
            if (dataArray[parentIndex].compareTo(newNode) > 0) {
                this.swap(currentNodeIndex, parentIndex);
                // System.out.println("swap " + currentNodeIndex + " and "+parentIndex);
                currentNodeIndex = parentIndex;
            } else {
                break;
            }
        }
        count++;
    }

    /**
     * removes and returns the highest priority Huffman Tree Node from the Array Heap and reorders the heap (heapify).
     *
     * @return the root
     */
    public HuffmanTreeNode<E> removeMin() {
        if (count > 1) {
            // get data from root
            var _data = dataArray[1];
            count--;
            // replace root with the right most data
            dataArray[1] = dataArray[count];
            // delete the right most data
            dataArray[count] = null;
            // re-order the tree (Heapify from top to bottom)
            int currentIndex = 1;
            int leftChildIndex, rightChildIndex;
            while (currentIndex < count) {
                leftChildIndex = 2 * currentIndex;
                rightChildIndex = leftChildIndex + 1;
                // if left and right child both exist (according to count, and note that right index is left index+1)
                if (rightChildIndex < count) {
                    // if current Node is bigger than any one of two children, then need to swap the current Node with its child
                    if (this.dataArray[currentIndex].compareTo(this.dataArray[leftChildIndex]) > 0 || this.dataArray[currentIndex].compareTo(this.dataArray[rightChildIndex]) > 0) {
                        if (this.dataArray[rightChildIndex].compareTo(this.dataArray[leftChildIndex]) > 0) {
                            this.swap(leftChildIndex, currentIndex);
                            currentIndex = leftChildIndex;
                        } else {
                            this.swap(rightChildIndex, currentIndex);
                            currentIndex = rightChildIndex;
                        }
                    } else {
                        // if current Node is smaller than its children, then it is on the right place!
                        break;
                    }
                }
                // if only left Node exists
                else if (leftChildIndex < count && this.dataArray[currentIndex].compareTo(this.dataArray[leftChildIndex]) > 0) {
                    this.swap(leftChildIndex, currentIndex);
                    currentIndex = leftChildIndex;
                } else {
                    // we reach the leaf (or when there is an only bigger left)! Done!
                    break;
                }
            }
            return _data;
        } else {
            // you cannot remove stuff when the heap is empty!
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

    public int size() {
        return count - 1;
    }

}
