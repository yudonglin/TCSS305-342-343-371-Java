public class DummyNodeQueue<E> {
    private final Node<E> dummy = new Node<>(null);
    private Node<E> head = dummy;
    private Node<E> rear = dummy;

    public void enqueue(E data) {
        if (dummy.next == null) {
            head = new Node<>(data);
            rear = head;
            dummy.next = head;
        } else {
            rear.next = new Node<>(data);
            rear = rear.next;
        }
    }

    public E dequeue() {
        if (head != null) {
            var data = head.getData();
            head = head.next;
            if (head == null) {
                head = dummy;
                rear = dummy;
                dummy.next = null;
            }
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
