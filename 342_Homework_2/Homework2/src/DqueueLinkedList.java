public class DqueueLinkedList<T> {
    /**
     * A Node front & rear to point to the front and end of the Queue
     */
    Node<T> front, rear;
    /**
     * An int count to keep track of number of elements in the Queue
     */
    private int count;

    /**
     * Creates an empty Queue
     */
    public DqueueLinkedList() {
        count = 0;
        front = rear = null;
    }

    public void enqueueFront(T elem) {
        // Create a new node
        Node<T> temp = new Node<T>(elem);

        // Check if the Queue is empty
        if (count == 0) {
            front = rear = temp;
        } else {
            // Add element to the front of the Queue
            temp.next = front;
            front = temp;
        }
        count++;       // update count
    }

    public void enqueueRear(T elem) {
        // Create a new node
        Node<T> temp = new Node<T>(elem);

        // Check if the Queue is empty
        if (count == 0) {
            front = rear = temp;
        } else {
            // Add element to the rear of the Queue
            rear.next = temp;
            rear = temp;
        }
        count++;       // update count
    }

    public T dequeueFront() {
        // check if the Queue is full
        if (count == 0) {
            throw new IndexOutOfBoundsException("Queue Underflow");
        }
        // store front element and update front
        T result = front.getData();
        front = front.next;

        // Check if front is pointint to null
        if (front == null) {
            rear = front;
        }
        count--;       // update count
        return result;
    }

    public T dequeueRear() {
        // check if the Queue is full
        if (count == 0) {
            throw new IndexOutOfBoundsException("Queue Underflow");
        } else {
            T result = rear.getData();
            if (count > 1) {
                var current = front;
                while (current.next.next != null) {
                    current = current.next;
                }
                current.next = null;
                rear = current;
            } else {
                front = rear = null;
            }
            count--;       // update count
            return result;
        }
    }
}
