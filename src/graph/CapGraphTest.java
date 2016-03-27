package graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
 
	}

	@Test
	public void testAddVertex() { 
		assertEquals("Null input fail", 5, testGraph.exportGraph().size());
		testGraph.addVertex(6);
		assertEquals("Null input fail", 6, testGraph.exportGraph().size());
	}

	@Test
	public void testAddEdge() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEgonet() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSCCs() {
		fail("Not yet implemented");
	}

	@Test
	public void testExportGraph() {
		fail("Not yet implemented");
	}
}
