package group787.utor.flylocity.driver;

import java.util.*;

/**
 * Graph class to be used by the algorithm class.
 */
public class Graph {
	
	private Map<String, LinkedHashSet<String>> map 
	= new HashMap<String, LinkedHashSet<String>>();
	
	/**
	 * Add an edge from first node to the second node.
	 * @param node1 first node to add a node to
	 * @param node2 node to be added
	 */
	public void addEdge(String node1, String node2) {
        LinkedHashSet<String> adjacent = map.get(node1);
        if(adjacent==null) {
            adjacent = new LinkedHashSet<String>();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }
	
	/**
	 * Add an unidirectional edge to two nodes.
	 * @param node1 node to add an edge to the other node
	 * @param node2 node to add an edge to the other node
	 */
    public void addTwoWayVertex(String node1, String node2) {
        addEdge(node1, node2);
        addEdge(node2, node1);
    }
    
    /**
     * Check if two nodes have an edge between each other.
     * @param node1 node to check if it has an edge
     * @param node2 node to check if node1 has an edge to
     * @return
     */
    public boolean isConnected(String node1, String node2) {
        Set<String> adjacent = map.get(node1);
        if(adjacent==null) {
            return false;
        }
        return adjacent.contains(node2);
    }
    
    /**
     * Return adjacent nodes of a node in this graph.
     * @param last the string key of this node in the graph
     * @return LinkedList of the adjacents of this node
     */
    public LinkedList<String> adjacentNodes(String last) {
        LinkedHashSet<String> adjacent = map.get(last);
        if(adjacent==null) {
            return new LinkedList<String>();
        }
        return new LinkedList<String>(adjacent);
    }

}
