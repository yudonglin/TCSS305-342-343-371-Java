package src;

public class Node {
    // link to next node in the list
    public Node next;
    // data stored in this node
    private char data;

    // post: constructs a node with given data and null link
    public Node(char data) {
        this.data = data;
        this.next = null;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }
}
