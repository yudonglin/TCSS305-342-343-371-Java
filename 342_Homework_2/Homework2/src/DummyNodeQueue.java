public class DummyNodeQueue<E> {
    private final Node<E> dummy = new Node<>(null);
    private Node<E> head = dummy;
    private Node<E> rear = dummy;
    private int count = 0;

    public void enqueue(E data) {
        if (count == 0) {
            rear = head = new Node<>(data);
            dummy.next = head;
        } else {
            rear.next = new Node<>(data);
            rear = rear.next;
        }
        count++;
    }

    public E dequeue() {
        if (count > 0) {
            var data = head.getData();
            if (count == 1) {
                head = rear = dummy;
                dummy.next = null;
            } else {
                head = head.next;
                dummy.next = head;
            }
            count--;
            return data;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getRear() {
        return rear;
    }

    public Node<E> getDummy() {
        return dummy;
    }

    @Override
    public String toString() {
        // if the linked list is empty, then just return an empty string
        if (dummy.next == null) {
            return "[]";
        } else {
            // using a StringBuilder for constructing the result
            var name = new StringBuilder();
            name.append("[");
            // if list is not empty, then there will be a head
            name.append(this.head.getData());
            var current = this.head.next;
            // loop through the linked list and add the dara to the builder
            while (current != null) {
                name.append("->");
                name.append(current.getData());
                current = current.next;
            }
            name.append("]");
            // return the result
            return name.toString();
        }
    }
}
