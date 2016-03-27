package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author Sudharaka Palamakumbura
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {

	// Initialization of fields (number of vertices and edges)
	private int numVertices;
	private int numEdges;
	
	private HashMap<Integer, HashSet<Integer>> adjList = new HashMap<Integer, HashSet<Integer>>();
	
	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		adjList.put(num, new HashSet<Integer>());
		numVertices++;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		adjList.get(from).add(to);
		numEdges++;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		return adjList;
	}
	
	public static void main(String[] args){
		CapGraph testGraph = new CapGraph();
		testGraph.addVertex(1);
		testGraph.addVertex(2);
		testGraph.addVertex(3);
		testGraph.addVertex(4);
		testGraph.addVertex(5);
		
		testGraph.addEdge(1, 4);
		testGraph.addEdge(2, 3);
		testGraph.addEdge(4, 1);
		
	    System.out.println(testGraph.exportGraph());
	}

}
