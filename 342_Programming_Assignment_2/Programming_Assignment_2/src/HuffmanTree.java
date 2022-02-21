public class HuffmanTree<E> {

    public final HuffmanTreeNode<E> root;

    public HuffmanTree(int totalPriority) {
        root = new HuffmanTreeNode<>(null, totalPriority);
    }

    public void setLeft(HuffmanTreeNode<E> leftNode) {
        root.left = leftNode;
    }

    public void setRight(HuffmanTreeNode<E> rightNode) {
        root.right = rightNode;
    }

}
