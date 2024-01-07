import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.Random;

//Algorithmus
//Voraussetzung:
//Sei G = (V; E) ein zusammenhängender Graph, der nur
//Knoten mit geradem Grad aufweist.


public class Hierholzer {

    public Graph testGraph() {
        Graph toUse = new MultiGraph("test");
        boolean isDirected = false;
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

    public Graph getGraph(){
        //To-Do: Hier Graphenerzeugung nutzen | Nikita
        return testGraph();
    }

    //i. Wähle einen beliebigen Knoten v0 des Graphen

    /**
     * chooses a random starting node v0
     * @param graph - graph from which to chose
     * @return v0
     */
    public Node choseStartingNode(Graph graph){
        Random random = new Random();
        return graph.getNode(random.nextInt(0, getGraph().nodes().toArray().length-1));
    }

    //i ff und konstruiere von v0 ausgehend einen
    // Unterkreis K in G, der alle Eigenschaften
    // eines Eulerkreises besitzt.
    public void makeLoop(){
        // TODO: 1/7/2024  
    }
    
    //ii. Vernachlässige nun alle Kanten dieses
    //Unterkreises.

    public void ignoreEdges(){
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
