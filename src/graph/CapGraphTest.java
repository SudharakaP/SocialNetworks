package graph;

import static org.junit.Assert.*;

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
		Graph newgraph = testGraph.getEgonet(2);
		assertEquals("Test egonet correctness", 4, newgraph.exportGraph().size());
		assertEquals("Test egonet correctness", true, newgraph.exportGraph().get(2).contains(3));
		assertEquals("Test egonet correctness", true, newgraph.exportGraph().get(2).contains(4));
		assertEquals("Test egonet correctness", true, newgraph.exportGraph().get(2).contains(5));
		assertEquals("Test egonet correctness", true, newgraph.exportGraph().get(3).contains(4));
		assertEquals("Test egonet correctness", false, newgraph.exportGraph().get(3).contains(5));
	}

	@Test
	public void testGetSCCs() {
		fail("Not yet implemented");
	}

	@Test
	public void testExportGraph() {
		Integer[] test = {3,4};
		assertArrayEquals("Check for correct edges", test, testGraph.exportGraph().get(1).toArray());
	}
}
