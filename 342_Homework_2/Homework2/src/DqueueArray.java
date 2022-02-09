public class DqueueArray<T> {
    /**
     * Default capacity of the queue array
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * An int array to implement a queue
     */
    private final T[] q;
    /**
     * An int front (dequeue), rear(enqueue)and count(# of elements) of the queue
     */
    private int front, rear, count;

    /**
     * Creates a Queue with a default capacity of 10
     */
    public DqueueArray() {
        q = (T[]) (new Object[DEFAULT_CAPACITY]);
        front = rear = count = 0;
    }

    public void enqueueFront(T elem) {
        // check if the Queue is full
        if (count == q.length)
            throw new IndexOutOfBoundsException("Queue Overflow");
        front = front < 1 ? q.length - 1 : front - 1;
        q[front] = elem;
        count++;
    }

    public void enqueueRear(T elem) {
        // check if the Queue is full
        if (count == q.length)
            throw new IndexOutOfBoundsException("Queue Overflow");
        // add element to the Queue & update rear
        q[rear] = elem;
        rear = (rear + 1) % q.length;
        // update count
        count++;
    }

    public T dequeueFront() {
        // check if the Queue is full
        if (count == 0)
            throw new IndexOutOfBoundsException("Queue Underflow");
        // store the first element and update front
        T result = q[front];
        q[front] = null;        // only for java objects
        front = (front + 1) % q.length;

        // update count
        count--;
        return result;
    }

    public T dequeueRear() {
        // check if the Queue is full
        if (count == 0)
            throw new IndexOutOfBoundsException("Queue Underflow");
        // store the first element and update front
        rear = rear < 1 ? q.length - 1 : rear - 1;
        T result = q[rear];
        q[rear] = null;        // only for java objects

        // update count
        count--;
        return result;
    }

    public T get(int index) {
        return q[index];
    }

}