public class Stack extends DoubleLinkedList {

    public void push(String data) {
        this.addRear(data);
    }

    public String peek() {
        return this.size() > 0 ? this.getRear().getData() : null;
    }

    public String pop() {
        return this.size() > 0 ? this.popRear().getData() : null;
    }
}
