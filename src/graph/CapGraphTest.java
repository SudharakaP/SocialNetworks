package graph;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class CapGraphTest {
	
	CapGraph testGraph;

	@Before
	public void setUp() throws Exception {
		 testGraph = new CapGraph();
		 testGraph.addVertex(1);
		 testGraph.addVertex(2);
		 testGraph.addVertex(3);
		 testGraph.addVertex(4);
		 testGraph.addVertex(5);
		 
		 testGraph.addEdge(1, 4);
		 testGraph.addEdge(1, 3);
		 testGraph.addEdge(2, 5);
		 testGraph.addEdge(4, 1);
		 testGraph.addEdge(2, 3);
		 testGraph.addEdge(2, 4);
		 testGraph.addEdge(3, 4);
 
	}

	@Test
	public void testAddVertex() { 
		assertEquals("Size before adding vertex", 5, testGraph.exportGraph().size());
		testGraph.addVertex(6);
		assertEquals("Size after adding vertex", 6, testGraph.exportGraph().size());
	}

	@Test
	public void testAddEdge() {
		assertEquals("Before adding edge 1 to 5", false, testGraph.exportGraph().get(1).contains(5));
		testGraph.addEdge(1, 5);
		assertEquals("After adding edge 1 to 5", true, testGraph.exportGraph().get(1).contains(5));
	}

	@Test
	public void testGetEgonet() {
		
		// test for correctness of egonet centered at 2
		Graph newgraph = testGraph.getEgonet(2);
		assertEquals("Test for egonet of 2", 4, newgraph.exportGraph().size());
		assertEquals("Test for egonet of 2", true, newgraph.exportGraph().get(2).contains(3));
		assertEquals("Test for egonet of 2", true, newgraph.exportGraph().get(2).contains(4));
		assertEquals("Test for egonet of 2", true, newgraph.exportGraph().get(2).contains(5));
		assertEquals("Test for egonet of 2", true, newgraph.exportGraph().get(3).contains(4));
		assertEquals("Test for egonet of 2", false, newgraph.exportGraph().get(3).contains(5));
		
		// test for empty graph when egonet vertex is not in the graph
		Graph nullgraph = testGraph.getEgonet(10);
		assertEquals("Test for empty egonet", 0, nullgraph.exportGraph().size());
		
	}

	@Test
	public void testGetSCCs() {
		
		CapGraph testGraph = new CapGraph();
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
		
		List<Graph> graphs = testGraph.getSCCs();
		System.out.println(graphs.get(0).exportGraph());
		System.out.println(graphs.get(1).exportGraph());
		System.out.println(graphs.get(2).exportGraph());
		System.out.println(graphs.get(3).exportGraph());
		
		try {
			System.out.println(graphs.get(4).exportGraph());
	        fail("Expected an IndexOutOfBoundsException to be thrown");
	    } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
	        
	    }
	}

	@Test
	public void testExportGraph() {
		Integer[] test = {3,4};
		assertArrayEquals("Check for correct edges", test, testGraph.exportGraph().get(1).toArray());
	}
	
	@Test
	public void testBFS(){
		CapGraph testGraph = new CapGraph();
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
