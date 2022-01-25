package src;

public class DoubleLinkedList {

    // the head of the list
    private DoubleNode head;
    private DoubleNode rear;
    private int count = 0;

    // post: constructs an empty list
    public DoubleLinkedList() {
        head = null;
        rear = null;
    }

    public DoubleNode getHead() {
        return head;
    }

    public DoubleNode getRear() {
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

    public void clear() {
        this.head = null;
        this.rear = null;
        this.count = 0;
    }

    public void add_rear(String data) {
        if (this.head != null) {
            this.rear.next = new DoubleNode(data);
            this.rear.next.prev = this.rear;
            this.rear = this.rear.next;
        } else {
            this.head = new DoubleNode(data);
            this.rear = this.head;
        }
        this.count++;
    }

    public void add_front(String data) {
        if (this.head != null) {
            var newNode = new DoubleNode(data);
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
            this.count++;
        } else {
            this.add_rear(data);
        }
    }

    public DoubleNode get(int index) {
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

    public void delete(String value) {
        if (this.head != null) {
            if (!this.head.getData().equals(value)) {
                var current = this.head.next;
                while (current != null) {
                    if (!current.getData().equals(value)) {
                        current = current.next;
                    } else {
                        if (current != this.rear) {
                            current.next.prev = current.prev;
                            current.prev.next = current.next;
                        } else {
                            this.rear = this.rear.prev;
                            this.rear.next = null;
                        }
                        this.count--;
                        break;
                    }
                }
            } else {
                this.head = this.head.next;
                if (this.head != null) {
                    this.head.prev = null;
                } else {
                    this.rear = null;
                }
                this.count--;
            }
        }
    }

    public void delete_all(String value) {
        var current = this.head;
        while (current != null) {
            if (current.getData().equals(value)) {
                this.count--;

                if (current != this.rear && current != this.head) {
                    current.next.prev = current.prev;
                    current.prev.next = current.next;
                } else if (current == this.head) {
                    this.head = this.head.next;
                    if (this.head != null) {
                        this.head.prev = null;
                    } else {
                        this.rear = null;
                        break;
                    }
                } else {
                    this.rear = this.rear.prev;
                    this.rear.next = null;
                    break;
                }
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
