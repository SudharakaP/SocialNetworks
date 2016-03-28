package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import util.GraphLoader;

/**
 * @author Sudharaka Palamakumbura
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {

	private Map<Integer, HashSet<Integer>> adjList = new HashMap<Integer, HashSet<Integer>>();
	private List<Graph> stronglyConnected = new ArrayList<Graph>();
	
	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		adjList.put(num, new HashSet<Integer>());
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		if (this.exportGraph().containsKey(from) && this.exportGraph().containsKey(to))
			adjList.get(from).add(to);
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		
		// If "center" is not contained in graph return empty graph
		if (!adjList.containsKey(center)) 
			return new CapGraph();
		
		CapGraph egonet = new CapGraph();
		egonet.addVertex(center);
		
		for (Integer vertex: adjList.get(center))
			egonet.addVertex(vertex);
		
		for (Integer from: egonet.adjList.keySet())
			for (Integer to: egonet.adjList.keySet())
				if (adjList.get(from).contains(to))
					egonet.addEdge(from, to);
	
		return egonet;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs(): Returns all Strongly Connected Components (SSCs) 
	 * of the graph.
	 */
	@Override
	public List<Graph> getSCCs() {

		Stack<Integer> finished = DFS(this.vertices(), false);
		
		CapGraph transpose = this.graphTanspose();
		
		transpose.DFS(finished, true);
		System.out.println(this.stronglyConnected);
		return stronglyConnected;
		
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		return (HashMap<Integer, HashSet<Integer>>)adjList;
	}
	
	/**
	 * This is a helper method for the getSSC method
	 * 
	 * @param vertices the vertices of the graph as a Stack
	 * @param sccList whether or not to add to the list of SCCs
	 * @strongCon if this parameter is "true" then graphs (strongly connected components)
	 * formed by the vertices are stored in "stronglyConnected"
	 * @return Stack<Integer> 
	 */
	public Stack<Integer> DFS(Stack<Integer> vertices, boolean strongCon){
		HashSet<Integer> visited = new HashSet<Integer>();
		Stack<Integer> finished = new Stack<Integer>();
		HashSet<Integer> scc = new HashSet<Integer>();
		
		while (!vertices.isEmpty()){
			Integer v = vertices.pop();
			
			if (!visited.contains(v))
				scc = DFSVisit(v, visited, finished);
				
			// piece together the strongly connected components, if strongCon == true
			if (strongCon){
				
				CapGraph graph = new CapGraph();
				// add all the vertices of the strongly connected components
				for (Integer vertex: scc)
					graph.addVertex(vertex);
				
				// add all the edges of the strongly connected components
				for (Integer vertex: scc)
					for (Integer a: adjList.get(vertex))
						graph.addEdge(vertex, a);
				
				stronglyConnected.add(graph);
			}			
		}
		return finished;
	}
	
	/**
	 * This is a helper method of BFS
	 * 
	 * @param vertex
	 * @param visited 
	 * @param finished
	 */
	public HashSet<Integer> DFSVisit(Integer vertex, HashSet<Integer> visited, Stack<Integer> finished){
		
		HashSet<Integer> strongConn = new HashSet<Integer>();
		visited.add(vertex);
		strongConn.add(vertex);
		
		for (Integer n: exportGraph().get(vertex))
			if (!visited.contains(n))
				DFSVisit(n, visited, finished);
		
		finished.push(vertex);
		return strongConn;
	}
	
	// returns the list of vertices of the graph
	public Stack<Integer> vertices(){
		Stack<Integer> vertices = new Stack<Integer>();
		
		// creating the list of vertices
		for (Integer vertex: adjList.keySet()){
			vertices.push(vertex);
		}
		
		return vertices;
	}
	
	/**
	 * This method returns the transpose of the graph
	 * 
	 * @param graph
	 * @return transpose of graph
	 */
	public CapGraph graphTanspose(){
		
		CapGraph transpose = new CapGraph();
		
		// copy over the vertices
		for (Integer vertex: adjList.keySet())
			transpose.addVertex(vertex);
		
		//copy the transpose of edges
		for (Integer vertex: adjList.keySet()){
			HashSet<Integer> edgeSet = adjList.get(vertex);
			for (Integer v: edgeSet)
				transpose.addEdge(v, vertex);
		}
		return transpose;
	}
	
	public static void main(String[] args){
		CapGraph testGraph = new CapGraph();
		GraphLoader.loadGraph(testGraph, "data/facebook_1000.txt");
		System.out.println(testGraph.exportGraph());
	}

}
