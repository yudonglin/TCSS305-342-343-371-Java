package src;

public class LinkedList<E> {

    // the head of the list
    private Node<E> head;
    private Node<E> rear;
    private int count = 0;

    // post: constructs an empty list
    public LinkedList() {
        head = null;
        rear = null;
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getRear() {
        return rear;
    }

    public int size() {
        return count;
    }

    public String toString() {
        if (count <= 0) {
            return "[]";
        } else {
            var name = new StringBuilder();
            name.append('[');
            name.append(this.head.getData());
            var current = this.head.next;
            while (current != null) {
                name.append("->");
                name.append(current.getData());
                current = current.next;
            }
            name.append(']');
            return name.toString();
        }
    }

    public void add(E data) {
        if (this.head != null) {
            this.rear.next = new Node<E>(data);
            this.rear = this.rear.next;
        } else {
            this.head = new Node<E>(data);
            this.rear = this.head;
        }
        this.count++;
    }

    public void clear() {
        this.head = null;
        this.rear = null;
        this.count = 0;
    }

    public Node<E> get(int index) {
        if (index < count) {
            if (index == count - 1) {
                return this.rear;
            } else {
                var current = this.head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.next;
                }
                return current;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void swap(int i, int j) {
        if (i != j) {
            var node_j_prev = this.head;
            for (int k = 0; k < j-2; k++) {
                node_j_prev = node_j_prev.next;
            }
            var node_j = node_j_prev.next;
            var node_j_next = node_j.next;
            if (i >= 2) {
                var node_i_prev = this.head;
                for (int k = 0; k < i-2; k++) {
                    node_i_prev = node_i_prev.next;
                }
                var node_i = node_i_prev.next;
                var node_i_next = node_i.next;
                node_j_prev.next = node_i;
                node_i_prev.next = node_j;
                node_i.next = node_j_next;
                node_j.next = node_i_next;
            } else {
                var node_i = head;
                node_j.next = head.next;
                head = node_j;
                node_i.next = node_j_next;
                node_j_prev.next = node_i;
            }
        }
    }

    public void deleteDuplicates() {
        var current = this.head;
        while (current != null) {
            var iterator = current;
            while (iterator != null) {
                if (iterator.next == null) {
                    break;
                } else if (iterator.next.getData().equals(current.getData())) {
                    iterator.next = iterator.next.next;
                    this.count--;
                }
                iterator = iterator.next;
            }
            current = current.next;
        }
    }

    public boolean containsCycle() {
        var walker = this.head;
        var runner = this.head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) {
                return true;
            }
        }
        return false;
    }

}
