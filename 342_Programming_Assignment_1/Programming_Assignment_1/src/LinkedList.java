package src;

public class LinkedList implements LinkedListInterface {

    // the head of the list
    private Node head;
    private Node rear;
    private int count = 0;

    // post: constructs an empty list
    public LinkedList() {
        head = null;
        rear = null;
    }

    public Node getHead() {
        return head;
    }

    public Node getRear() {
        return rear;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void add(char data) {
        if (this.head != null) {
            this.rear.next = new Node(data);
            this.rear = this.rear.next;
        } else {
            this.head = new Node(data);
            this.rear = this.head;
        }
        this.count++;
    }

    @Override
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

    public void clear() {
        this.head = null;
        this.rear = null;
        this.count = 0;
    }

    public Node get(int index) {
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

    public void deleteDuplicates() {
        var current = this.head;
        while (current != null) {
            var iterator = current;
            while (iterator != null) {
                if (iterator.next == null) {
                    break;
                } else if (iterator.next.getData() == current.getData()) {
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
