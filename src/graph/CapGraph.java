package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import util.GraphLoader;

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
	
	private Map<Integer, HashSet<Integer>> adjList = new HashMap<Integer, HashSet<Integer>>();
	
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
		return (HashMap<Integer, HashSet<Integer>>)adjList;
	}
	
	public static void main(String[] args){
		CapGraph testGraph = new CapGraph();
		GraphLoader.loadGraph(testGraph, "data/facebook_1000.txt");
		System.out.println(testGraph.exportGraph());
	}

}
