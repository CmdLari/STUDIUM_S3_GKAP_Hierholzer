import org.graphstream.graph.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hierholzer {

    private final Graph graph;
    private final List<Edge> edgesSorted;
    private final List<Node> originalNodes = new ArrayList<>();
    private final List<Edge> originalEdges = new ArrayList<>();


    public Hierholzer(Graph graph) {
        //origin graph
        this.graph = graph;
        this.edgesSorted = new ArrayList<>();
        originalNodes.addAll(graph.nodes().toList());
        originalEdges.addAll(graph.edges().toList());
    }

    public boolean checkGraph(Graph graph) {
        if (originalNodes.isEmpty() || originalEdges.isEmpty()) {
            System.err.println("Please chose a valid graph: Undirected, not empty.");
            return false;
        }
        //if (graph.isDirected){
        //  System.err.println("Please chose a valid graph: Undirected, not empty.");
        //  return false}
        for (Node node : graph) {
            if (node.edges().toList().size() % 2 == 1) {
                System.err.println("Nodes in this graph may have an uneven number of edges.");
                return false;
            }
        }
        return true;
    }


    /**
     * chooses a random starting node v0
     *
     * @return v0
     */
    public Node choseStartingNode() {
        Random random = new Random();
        return graph.getNode(random.nextInt(0, graph.nodes().toArray().length - 1));
    }

    /**
     * starts from v0 node and makes an Eulerian trail
     * removes used edges from graph
     */
    public void startHierholzer() {
        if (checkGraph(graph)) {
            Node v0 = choseStartingNode();
            makeLoop(v0);
            System.out.printf("\n%s", edgesSorted);
            System.out.print("\n###### The Hierholzer Algorithmm is complete ######");
            System.out.print("\n_____________________________________________________");
            System.out.print("\nSaved the following:");
        }
    }

    /**
     * starts from active node and makes an Eulerian trail
     * removes used edges from graph
     *
     * @param node starting node
     */
    public void makeLoop(Node node) {
        Node activeNode = node;
        List<Node> usedNodes = new ArrayList<>();
        usedNodes.add(activeNode);
        while (activeNode.edges().findAny().isPresent() && activeNode.edges().toList().get(0).getOpposite(activeNode) != node) {
            activeNode = addEdges(activeNode, usedNodes);
        }
        if (activeNode.edges().findAny().isPresent() && activeNode.edges().toList().get(0).getOpposite(activeNode) == node) {
            addEdges(activeNode, usedNodes);
        }

        System.out.printf("\nEulerian Sub Loop: %s", usedNodes);
        for (int i = 0; i < usedNodes.size() - 1; i++) {
            if (graph.getNode(usedNodes.get(i).getId()).edges().findAny().isPresent()) {
                activeNode = usedNodes.get(i);
                makeLoop(activeNode);
            }
        }
    }

    /**
     * adds nodes to list of used nodes, adds nodes and edges to the sub graph, removes used nodes from graph
     *
     * @param activeNode - node from which we start
     * @param usedNodes  - list of nodes used for the sub loop
     * @return new node from which to start
     */
    private Node addEdges(Node activeNode, List<Node> usedNodes) {
        Node oppositeNode = activeNode.edges().toList().get(0).getOpposite(activeNode);
        Edge chosenEdge = activeNode.edges().toList().get(0);
        edgesSorted.add(chosenEdge);
        String activeId = activeNode.getId();
        String oppositeId = oppositeNode.getId();
        System.out.printf("\n\tadded Edge: %s -- %s", activeId, oppositeId);
        graph.removeEdge(chosenEdge);
        activeNode = oppositeNode;
        usedNodes.add(activeNode);
        return activeNode;
    }

}