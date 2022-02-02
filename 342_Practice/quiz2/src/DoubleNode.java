public class DoubleNode {

    // data stored in this node
    private final String data;
    // link to next node in the list
    public DoubleNode next;
    // link to previous node in the list
    public DoubleNode prev;

    // post: constructs a node with given data and null link
    public DoubleNode(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public String getData() {
        return data;
    }

}
