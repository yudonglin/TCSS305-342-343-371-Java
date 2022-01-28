package src;

public class Node<E> {
    // link to next node in the list
    public Node<E> next;
    // data stored in this node
    private E data;

    // post: constructs a node with given data and null link
    public Node(E data) {
        this.data = data;
        this.next = null;
    }

    public String getData() {
        return data.toString();
    }

    public void setData(E data) {
        this.data = data;
    }
}
