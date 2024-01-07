import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.io.FileNotFoundException;

public class Client {
    public static void main(String[] args) throws FileNotFoundException {
        Hierholzer hierholzer = new Hierholzer(testGraph());
        hierholzer.makeLoop();
    }

    public static Graph testGraph() {
        Graph toUse = new MultiGraph("test");
        toUse.addNode("a");
        toUse.addNode("b");
        toUse.addNode("c");
        toUse.addNode("d");
        toUse.addNode("e");
        toUse.addEdge("1", "a", "b");
        toUse.addEdge("2", "a", "c");
        toUse.addEdge("3", "b", "c");
        toUse.addEdge("4", "c", "e");
        toUse.addEdge("5", "c", "d");
        toUse.addEdge("6", "d", "e");
        return toUse;
    }
}
