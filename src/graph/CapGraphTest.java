package graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import util.GraphLoader;

/**
 * Test Class for CapGraph.java
 * 
 * @author Sudharaka Palamakumbura
 *
 */
public class CapGraphTest {
	
	CapGraph testGraph;

	@Before
	public void setUp() throws Exception {
		testGraph = new CapGraph();
		testGraph.addVertex(32);
		testGraph.addVertex(50);
		testGraph.addVertex(44);
		testGraph.addVertex(25);
		testGraph.addVertex(23);
		testGraph.addVertex(65);
		testGraph.addVertex(18);
		
		testGraph.addEdge(32, 50);
		testGraph.addEdge(32, 44);
		testGraph.addEdge(44, 50);
		
		testGraph.addEdge(25, 23);
		testGraph.addEdge(25, 65);
		testGraph.addEdge(25, 18);
		
		testGraph.addEdge(65, 23);
		testGraph.addEdge(44, 18);
		testGraph.addEdge(18, 23);
		testGraph.addEdge(23, 25);
		testGraph.addEdge(23, 18);
 
	}

	@Test
	public void testAddVertex() { 
		assertEquals("Size before adding vertex", 7, testGraph.exportGraph().size());
		testGraph.addVertex(6);
		assertEquals("Size after adding vertex", 8, testGraph.exportGraph().size());
	}

	@Test
	public void testAddEdge() {
		assertEquals("Before adding edge 18 to 50", false, testGraph.exportGraph().get(18).contains(50));
		testGraph.addEdge(18, 50);
		assertEquals("After adding edge 18 to 50", true, testGraph.exportGraph().get(18).contains(50));
	}

	@Test
	public void testGetEgonet() {
		
		// test for correctness of egonet centered at 2
		Graph newgraph = testGraph.getEgonet(25);
		assertEquals("Test for egonet of 25", 4, newgraph.exportGraph().size());
		assertEquals("Test for egonet of 25", true, newgraph.exportGraph().get(25).contains(23));
		assertEquals("Test for egonet of 25", true, newgraph.exportGraph().get(25).contains(18));
		assertEquals("Test for egonet of 25", true, newgraph.exportGraph().get(25).contains(65));
		assertEquals("Test for egonet of 25", true, newgraph.exportGraph().get(18).contains(23));
		assertEquals("Test for egonet of 25", true, newgraph.exportGraph().get(65).contains(23));
		assertEquals("Test for egonet of 25", false, newgraph.exportGraph().get(65).contains(25));
		
		// test for empty graph when egonet vertex is not in the graph
		Graph nullgraph = testGraph.getEgonet(10);
		assertEquals("Test for empty egonet", 0, nullgraph.exportGraph().size());
		
	}

	@Test
	public void testGetSCCs() {
		
		CapGraph testGraph = new CapGraph();
		GraphLoader.loadGraph(testGraph, "data/scc/test_5.txt");
		List<Graph> scc = new ArrayList<Graph>();
		scc = testGraph.getSCCs();
		System.out.println(scc.get(0).exportGraph());
		System.out.println(scc.get(1).exportGraph());
		System.out.println(scc.get(2).exportGraph());
		System.out.println(scc.get(3).exportGraph());
		System.out.println(scc.get(4).exportGraph());
		
		try {
			System.out.println(scc.get(5).exportGraph());
	        fail("Expected an IndexOutOfBoundsException to be thrown");
	    } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
	        
	    }
	}

	@Test
	public void testExportGraph() {
		assertEquals("Check for correct vertices", true, testGraph.exportGraph().containsKey(32));
		assertEquals("Check for correct vertices", true, testGraph.exportGraph().containsKey(50));
		assertEquals("Check for correct vertices", true, testGraph.exportGraph().containsKey(44));
		assertEquals("Check for correct vertices", true, testGraph.exportGraph().containsKey(25));
		assertEquals("Check for correct vertices", true, testGraph.exportGraph().containsKey(23));
		assertEquals("Check for correct vertices", true, testGraph.exportGraph().containsKey(18));
		assertEquals("Check for correct vertices", true, testGraph.exportGraph().containsKey(65));
		
		HashSet<Integer> value = new HashSet<Integer>();
		value.add(65);
		value.add(18);
		value.add(23);
		
		assertEquals("Check for correct edges", true, testGraph.exportGraph().containsValue(value));
	}
	
	@Test
	public void testBFS(){
		
		assertEquals("Correct return size of BFS", 7, testGraph.DFS(testGraph.vertices(), false).size());
		assertEquals("Correct return element of BFS", true, testGraph.DFS(testGraph.vertices(), false).contains(65));
		assertEquals("Correct return element of BFS", true, testGraph.DFS(testGraph.vertices(), false).contains(23));
		assertEquals("Correct return element of BFS", true, testGraph.DFS(testGraph.vertices(), false).contains(25));
		assertEquals("Correct return element of BFS", true, testGraph.DFS(testGraph.vertices(), false).contains(50));
		assertEquals("Correct return element of BFS", true, testGraph.DFS(testGraph.vertices(), false).contains(44));
		assertEquals("Correct return element of BFS", true, testGraph.DFS(testGraph.vertices(), false).contains(18));
		assertEquals("Correct return element of BFS", true, testGraph.DFS(testGraph.vertices(), false).contains(32));
		
		assertEquals("Correct return element of BFS", false, testGraph.DFS(testGraph.vertices(), false).contains(15));
	}
}
