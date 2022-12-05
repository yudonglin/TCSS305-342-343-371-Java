import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PowerGrid {
    private static boolean existCycle(HashSet<Edge> edges, Vertex start, Vertex target) {
        for (final Edge _edge : edges) {
            if (_edge.getFirstEndpoint() == start) {
                if (_edge.getSecondEndpoint() == target || existCycle(edges, _edge.getSecondEndpoint(), target)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Set<Edge> kruskal(SimpleGraph graph) {
        var edges = new HashSet<Edge>();

        BinaryHeap heap = new BinaryHeap();

        Iterator i;

        System.out.println("Iterating through adjacency lists...");
        for (i = graph.vertices(); i.hasNext(); ) {
            Vertex v = (Vertex) i.next();
            System.out.println("Vertex " + v.getName());
            Iterator j;

            for (j = graph.incidentEdges(v); j.hasNext(); ) {
                Edge e = (Edge) j.next();
                System.out.println("  found edge " + e.getName());
                heap.insert(e);
            }
        }

        while (!heap.isEmpty()) {
            final Edge _edge = heap.deleteMin();
            if (!existCycle(edges, _edge.getFirstEndpoint(), _edge.getSecondEndpoint())) {
                edges.add(_edge);
            }
        }

        return edges;
    }

    public static void main(String[] args) {
        SimpleGraph G = new SimpleGraph();

        Vertex a = G.insertVertex(null, "a");
        Vertex b = G.insertVertex(null, "b");
        Vertex c = G.insertVertex(null, "c");

        Edge e1 = G.insertEdge(a, b, 2, "X");
        Edge e2 = G.insertEdge(b, c, 4, "Y");

        for (final Edge _edge : kruskal(G)) {
            System.out.printf("%s %s\n", _edge.getFirstEndpoint().getName(), _edge.getSecondEndpoint().getName());
        }
    }
}
