import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Algorithmus
//Voraussetzung:
//Sei G = (V; E) ein zusammenhängender Graph, der nur
//Knoten mit geradem Grad aufweist.


public class Hierholzer {

    private final Graph graphOrigin;
    private final Graph graphWIP;
    private final List<Edge> edgesSorted;
    private final Node v0;

    public Hierholzer(Graph graph){
        //origin graph
        this.graphOrigin = graph;
        this.graphWIP = graphOrigin;
        this.edgesSorted = new ArrayList<>();
        this.v0 = choseStartingNode();

    }


    /**
     * chooses a random starting node v0
     * @return v0
     */
    public Node choseStartingNode(){
        Random random = new Random();
        return graphOrigin.getNode(random.nextInt(0, graphOrigin.nodes().toArray().length-1));
    }

    /**
     * starts from v0 node and makes an Eulerian trail
     * removes used edges from graph
     */
    public void makeLoop(){
        if (!graphWIP.edges().toList().isEmpty()){
            makeLoop(v0);}
        System.out.printf("\n%s", edgesSorted);
        System.err.print("\n###### The Hierholzer Algorithmm is complete ######");
    }

    /**
     * starts from active node and makes an Eulerian trail
     * removes used edges from graph
     * @param node starting node
     */
    public void makeLoop(Node node){
        Node activeNode=node;
        List<Node> usedNodes = new ArrayList<>();
        usedNodes.add(activeNode);
        Graph eulerianLoop = new MultiGraph("Sub Loop");
        while (!activeNode.edges().toList().isEmpty() && activeNode.edges().toList().get(0).getOpposite(activeNode)!= node){
            activeNode = addEdges(activeNode, usedNodes, eulerianLoop);
        }
        if (!activeNode.edges().toList().isEmpty() && activeNode.edges().toList().get(0).getOpposite(activeNode)== node){
            activeNode = addEdges(activeNode, usedNodes, eulerianLoop);
        }

        System.out.printf("\nEulerian Sub Loop: %s", usedNodes);
        for (int i = 0; i < usedNodes.size()-1; i++) {
            if(graphWIP.getNode(usedNodes.get(i).getId()).edges().toList().size()!=0){
                //usedNodes.get(i).edges().toList()!=null
                activeNode=usedNodes.get(i);
                makeLoop(activeNode);
            }
        }
    }

    /**
     * adds nodes to list of used nodes, adds nodes and edges to the sub graph, removes used nodes from graph
     * @param activeNode - node from which we start
     * @param usedNodes - list of nodes used for the sub loop
     * @param eulerianLoop sub graph showing the eulerian loop
     * @return new node from which to start
     */
    private Node addEdges(Node activeNode, List<Node> usedNodes, Graph eulerianLoop) {
        Node oppositeNode = activeNode.edges().toList().get(0).getOpposite(activeNode);
        Edge chosenEdge = activeNode.edges().toList().get(0);
        edgesSorted.add(chosenEdge);
        String activeId = activeNode.getId();
        String oppositeId = oppositeNode.getId();
        if (eulerianLoop.getNode(activeId)==null){
            eulerianLoop.addNode(activeId);}
        if (eulerianLoop.getNode(oppositeId)==null){
            eulerianLoop.addNode(oppositeId);}
        eulerianLoop.addEdge((String.format("%s -%s", activeId, oppositeId)), eulerianLoop.getNode(activeId), eulerianLoop.getNode(oppositeId));
        System.out.printf("\n\tadded Edge: %s -- %s", activeId, oppositeId);
        graphWIP.removeEdge(chosenEdge);
        activeNode = oppositeNode;
        usedNodes.add(activeNode);
        return activeNode;
    }


    public void isEulerian(){
        // TODO: 1/7/2024
    }

    //iii. Am ersten Eckpunkt des ersten Unterkreises,
    //dessen Grad größer 0 ist, lässt man nun einen
    //weiteren Unterkreis entstehen, der wiederum
    //ein Eulerkreis ist.

    public void addLoops(){
        // TODO: 1/7/2024  
    }

    //iv. Erstelle so viele Unterkreise, bis alle Kanten
    //von einem Unterkreis durchlaufen wurden.

    public void checkCompletion(){
        // TODO: 1/7/2024  
    }

    //v. Nun erhält man den Eulerkreis, indem man mit
    //dem ersten Unterkreis beginnt und bei jedem
    //Schnittpunkt mit einem anderen Unterkreis,
    //den letzteren einfügt, und danach den ersten
    //Unterkreis wieder bis zu einem weiteren
    //Schnittpunkt oder dem Endpunkt fortsetzt

    public void unite(){
        // TODO: 1/7/2024  
    }

    public void saveGraph(){
        // TODO: 1/7/2024
    }
}
