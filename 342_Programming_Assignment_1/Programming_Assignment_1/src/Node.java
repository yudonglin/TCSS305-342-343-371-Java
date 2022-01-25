package src;

public class Node {
    // link to next node in the list
    public Node next;
    // data stored in this node
    private String data;

    // post: constructs a node with given data and null link
    public Node(String data) {
        this.data = data;
        this.next = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
