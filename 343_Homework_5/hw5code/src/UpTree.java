import java.util.HashMap;

final public class UpTree {

    // a hash map for storing all the nodes of the up tree for union operation
    private final HashMap<String, SubTreeNode> X;

    /**
     * constructor
     */
    public UpTree() {
        X = new HashMap<>();
    }

    /**
     * union 2 vertexes
     *
     * @param v1 the first vertex
     * @param v2 the second vertex
     */
    private void union(final String v1, final String v2) {
        final String v1parent = find(v1);
        final String v2parent = find(v2);
        if (!v1parent.equals(v2parent)) {
            final SubTreeNode v1parentNode = X.get(v1parent);
            final SubTreeNode v2parentNode = X.get(v2parent);
            if (v1parentNode.rank >= v2parentNode.rank) {
                v2parentNode.parent = v1parent;
                v1parentNode.rank += v2parentNode.rank;
            } else {
                v1parentNode.parent = v2parent;
                v2parentNode.rank += v1parentNode.rank;
            }
        }
    }

    /**
     * check whether it is safe to add an edge
     *
     * @param _edge the edge that will be added
     * @return whether adding this edge will NOT cause a loop
     */
    public boolean willNoCauseCycle(final Edge _edge) {
        final String theFirstEndpointName = (String) _edge.getFirstEndpoint().getName();
        final String theSecondEndpointName = (String) _edge.getSecondEndpoint().getName();
        if (find(theFirstEndpointName).equals(find(theSecondEndpointName))) {
            return false;
        }
        union(theFirstEndpointName, theSecondEndpointName);
        return true;
    }

    /**
     * find the absolute parent of a node (while also update it if needed)
     *
     * @param v1 the child node
     * @return the absolute parent of this node
     */
    private String find(final String v1) {
        final SubTreeNode v1node = X.get(v1);
        // if v1 is a need node that does not exist in the up tree, then add it to the tree
        if (v1node == null) {
            X.put(v1, new SubTreeNode(v1, 0));
            return v1;
        }
        // while v1 is not an absolute parent, then its parent is always "questionable"
        else if (!v1node.parent.equals(v1)) {
            v1node.parent = find(v1node.parent);
        }
        return v1node.parent;
    }

    /**
     * a data structure for managing node information
     */
    static final private class SubTreeNode {
        public String parent;
        public int rank;

        public SubTreeNode(final String parent, final int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }
}
