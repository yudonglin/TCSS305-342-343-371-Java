public class Queue extends DoubleLinkedList {

    public void push(String data) {
        this.addRear(data);
    }

    public String peek() {
        return this.size() > 0 ? this.getHead().getData() : null;
    }

    public String pop() {
        return this.size() > 0 ? this.popHead().getData() : null;
    }
}
