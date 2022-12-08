import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PowerGrid {

    /**
     * This method takes a graph as an input and produces a
     * set of edges that comprise a minimum spanning tree of the given graph.
     *
     * @param graph input graph
     * @return a set of edges that comprise a minimum spanning tree of the given graph.
     */
    public static Set<Edge> kruskal(final SimpleGraph graph) {
        final var edges = new HashSet<Edge>();

        // put all edges into the heap
        final BinaryHeap heap = new BinaryHeap();

        final Iterator i;

        for (i = graph.vertices(); i.hasNext(); ) {
            final Vertex v = (Vertex) i.next();
            final Iterator j;

            for (j = graph.incidentEdges(v); j.hasNext(); ) {
                final Edge e = (Edge) j.next();
                heap.insert(e);
            }
        }

        // create an up tree for tracking whether there will be a cycle
        final UpTree upTree = new UpTree();

        // deleteMin to get the smallest edge
        while (heap.isNotEmpty()) {
            final Edge _edge = heap.deleteMin();
            if (upTree.willNoCauseCycle(_edge)) {
                edges.add(_edge);
            }
        }

        return edges;
    }

    /**
     * let the user specify a graph file, calls the method kruskal for the specified graph as input, and outputs
     * the set of edges and the total cost of the MST.
     */
    public static void main(final String[] args) {
        final SimpleGraph G = new SimpleGraph();
        GraphInput.LoadSimpleGraph(G);
        final Set<Edge> setOfEdges = kruskal(G);
        System.out.printf("The set of edges (%d in total):\n", setOfEdges.size());
        double totalCost = 0;
        for (final Edge _edge : setOfEdges) {
            totalCost += (double) _edge.getData();
            System.out.printf("%s-%s\n", _edge.getFirstEndpoint().getName(), _edge.getSecondEndpoint().getName());
        }
        System.out.printf("The total cost of the MST: %f\n", totalCost);
    }
}
