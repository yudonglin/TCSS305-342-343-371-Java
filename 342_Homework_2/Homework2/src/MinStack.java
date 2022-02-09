public class MinStack {

    private Node<Integer> head;
    private int min_value = Integer.MAX_VALUE;

    public void push(int value) {
        int differenceOffset = value - min_value;
        if (head == null) {
            head = new Node<>(differenceOffset);
        } else {
            var newHead = new Node<>(differenceOffset);
            newHead.next = head;
            head = newHead;
        }
        if (value < min_value) {
            min_value = value;
        }
    }

    public int pop() {
        if (head != null) {
            var value = this.peek();
            if (head.getData() < 0) {
                min_value -= head.getData();
            }
            head = head.next;
            return value;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int peek() {
        if (head != null) {
            return head.getData() > 0 ? head.getData() + min_value : min_value;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getMin() {
        if (head != null) {
            return min_value;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

}
