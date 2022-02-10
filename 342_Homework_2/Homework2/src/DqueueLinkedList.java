public class DqueueLinkedList<T> {
    /**
     * A Node front & rear to point to the front and end of the Queue
     */
    DoublyNode<T> front, rear = null;
    /**
     * An int count to keep track of number of elements in the Queue
     */
    private int count = 0;


    public void enqueueFront(T elem) {
        // Create a new node
        DoublyNode<T> temp = new DoublyNode<>(elem);
        // Check if the Queue is empty
        if (count == 0) {
            front = rear = temp;
        } else {
            // Add element to the front of the Queue
            temp.next = front;
            front.prev = temp;
            front = temp;
        }
        count++;       // update count
    }

    public void enqueueRear(T elem) {
        // Create a new node
        DoublyNode<T> temp = new DoublyNode<>(elem);
        // Check if the Queue is empty
        if (count == 0) {
            front = rear = temp;
        } else {
            // Add element to the rear of the Queue
            rear.next = temp;
            temp.prev = rear;
            rear = temp;
        }
        count++;       // update count
    }

    public T dequeueFront() {
        // check if the Queue is empty
        if (count == 0) {
            throw new IndexOutOfBoundsException("Queue Underflow");
        }
        // store the front element and update front
        T result = front.getData();
        if (count > 1) {
            front = front.next;
            front.prev = null;
        } else {
            front = rear = null;
        }
        count--;       // update count
        return result;
    }

    public T dequeueRear() {
        // check if the Queue is empty
        if (count == 0) {
            throw new IndexOutOfBoundsException("Queue Underflow");
        }
        T result = rear.getData();
        if (count > 1) {
            rear = rear.prev;
            rear.next = null;
        } else {
            front = rear = null;
        }
        count--;       // update count
        return result;
    }
}
