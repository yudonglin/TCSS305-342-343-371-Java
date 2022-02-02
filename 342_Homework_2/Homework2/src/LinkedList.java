public class LinkedList<E> {

    // the head of the list
    private Node<E> head;
    private Node<E> rear;
    private int count = 0;

    /**
     * constructs an empty linked list
     */
    public LinkedList() {
        head = null;
        rear = null;
    }

    /**
     * @return the head of the linked list
     */
    public Node<E> getHead() {
        return head;
    }

    /**
     * @return the rear of the linked list
     */
    public Node<E> getRear() {
        return rear;
    }

    /**
     * @return the number of elements in the SLL
     */
    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count <= 0;
    }

    /**
     * @return a String representation of the SLL in the format (with the bracket)
     */
    public String toString() {
        return "[" + this.toStringWithoutBracket() + "]";
    }

    /**
     * @return a String representation of the SLL in the format (without the bracket)
     */
    public String toStringWithoutBracket() {
        // if the linked list is empty, then just return an empty string
        if (isEmpty()) {
            return "";
        } else {
            // using a StringBuilder for constructing the result
            var name = new StringBuilder();
            // if list is not empty, then there will be a head
            name.append(this.head.getData());
            var current = this.head.next;
            // loop through the linked list and add the dara to the builder
            while (current != null) {
                name.append("->");
                name.append(current.getData());
                current = current.next;
            }
            // return the result
            return name.toString();
        }
    }

    /**
     * takes a data element as a parameter, creates a new node and adds it to the existing SLL.
     *
     * @param data the data that will be added to the linked list
     */
    public void add(E data) {
        // if the linked list is not empty
        if (this.head != null) {
            this.rear.next = new Node<>(data);
            this.rear = this.rear.next;
        } else {
            // otherwise, set the head and rear to point to the newly created Node
            this.head = new Node<>(data);
            this.rear = this.head;
        }
        // add 1 to count
        this.count++;
    }

    /**
     * remove everything from the linked list
     */
    public void clear() {
        this.head = null;
        this.rear = null;
        this.count = 0;
    }


    /**
     * get stuff from the linked list based on the index
     *
     * @param index the location of the Node
     * @return the node
     */
    public Node<E> get(int index) {
        // make sure the index is inbound
        if (index < count) {
            if (index == count - 1) {
                return this.rear;
            } else {
                // loop through the linked list and locate the node
                var current = this.head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.next;
                }
                // return the node
                return current;
            }
        } else {
            // Ok, if you don't listen to me, then you will be in trouble
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * check whether two Linked Lists contain the same data
     *
     * @param other another Linked list
     * @return whether they are equal
     */
    public boolean equals(LinkedList<E> other) {
        // always check the size first
        if (other.size() == this.size()) {
            var this_current = this.head;
            var o_current = other.head;
            // loop through both linked list and compare their data
            while (this_current != null) {
                if (!this_current.getData().equals(o_current.getData())) {
                    return false;
                }
                this_current = this_current.next;
                o_current = o_current.next;
            }
            return true;
        } else {
            return false;
        }
    }

}
