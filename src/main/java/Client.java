import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Client {
    public static void main(String[] args) throws IOException {
        Graph graph = testGraph();
        Hierholzer hierholzer = new Hierholzer(graph);

        if (hierholzer.checkGraph(graph)) {
            String pc = System.getProperty("os.name");
            String team = "Test";
            String time = new SimpleDateFormat("yyMMdd_HHmm").format(Calendar.getInstance().getTime());
            String filename = String.format("%s_%s_%sEdgesGraph", time, team, graph.edges().count());
            FileWriter.saveGraph(team, "TestGraph", Long.toString(graph.edges().count()), pc, "IntelliJ", filename);

            for (int i = 0; i <= 10; i++) {
                long startTime = System.nanoTime();
                hierholzer.startHierholzer();
                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                String runtime = String.valueOf(duration / 1000000000.);
                FileWriter.appendGraph(String.valueOf(i), "DontKnow", runtime, filename);
            }
        }
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
