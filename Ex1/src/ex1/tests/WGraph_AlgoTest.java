package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;

class WGraph_AlgoTest {


	@Test
	void testCopy() {

		weighted_graph_algorithms wga = new WGraph_Algo();
		weighted_graph ga = new WGraph_DS();
		wga.init(ga);
		ga.addNode(0);
		ga.addNode(1);
		ga.addNode(7);
		ga.connect(1, 7, 3);
		weighted_graph ga1 = wga.copy();
		assertEquals(ga.getV().toString(), ga1.getV().toString());
		ga.addNode(5);
		assertNotEquals(ga.getV().toString(), ga1.getV().toString());
	}

	@Test
	void testIsConnected() {

		weighted_graph_algorithms wga = new WGraph_Algo();
		weighted_graph ga = new WGraph_DS();
		wga.init(ga);
		ga.addNode(0);
		ga.addNode(1);
		ga.addNode(2);
		ga.addNode(3);
		ga.connect(0, 1, 17);
		ga.connect(1, 2, 9);
		ga.connect(2, 3, 1.3);
		assertTrue(wga.isConnected());
		ga.removeEdge(1, 2);
		assertFalse(wga.isConnected());
		assertFalse(wga.isConnected());
	}

	@Test
	void testShortestPathDist() {

		weighted_graph_algorithms wga = new WGraph_Algo();
		WGraph_DS ga = new WGraph_DS();
		wga.init(ga);
		ga.addNode(1);
		ga.addNode(2);
		ga.addNode(3);
		ga.addNode(4);
		ga.addNode(5);
		ga.connect(1, 2, 15);
		ga.connect(1, 3, 2);
		ga.connect(1, 4, 17);
		ga.connect(1, 5, 20);
		ga.connect(3, 5, 4);
		ga.connect(5, 4, 1);
		assertEquals(wga.shortestPathDist(1, 4), 7);	
		
		
	}


	@Test
	void testShortestPath() {

		weighted_graph_algorithms wga = new WGraph_Algo();
		WGraph_DS ga = new WGraph_DS();
		wga.init(ga);
		ga.addNode(1);
		ga.addNode(2);
		ga.addNode(3);
		ga.addNode(4);
		ga.addNode(5);
		ga.connect(1, 2, 15);
		ga.connect(1, 3, 2);
		ga.connect(1, 4, 17);
		ga.connect(1, 5, 20);
		ga.connect(3, 5, 4);
		ga.connect(5, 4, 1);
		assertEquals(wga.shortestPath(1, 4).toString(), "[NodeInfo [_key=1, _info=|2,15.0|3,2.0|4,17.0|5,20.0|, _tag=0.0], NodeInfo [_key=3, _info=|1,2.0|5,4.0|, _tag=0.0], NodeInfo [_key=5, _info=|1,20.0|3,4.0|4,1.0|, _tag=0.0], NodeInfo [_key=4, _info=|1,17.0|5,1.0|, _tag=0.0]]");
		System.out.println(wga.shortestPath(1, 4).toString());
	}


	@Test
	void testSave() {

		WGraph_DS g = new WGraph_DS();
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.connect(1, 2, 15);
		g.connect(1, 3, 2);
		g.connect(1, 4, 17);
		g.connect(1, 5, 20);
		g.connect(3, 5, 4);
		g.connect(5, 4, 1);
		WGraph_DS g1 = new WGraph_DS();
		g1.addNode(1);
		g1.addNode(2);
		g1.addNode(3);
		weighted_graph_algorithms w = new WGraph_Algo();
		weighted_graph_algorithms w2 = new WGraph_Algo();
		w2.init(g1);
		assertEquals(w2.getGraph().getV().toString(), "[NodeInfo [_key=1, _info=, _tag=0.0], NodeInfo [_key=2, _info=, _tag=0.0], NodeInfo [_key=3, _info=, _tag=0.0]]");
		w.init(g);
		w.save("f1.txt");
		w2.load("f1.txt");
		assertEquals(w2.getGraph().getV().toString(), "[NodeInfo [_key=1, _info=|2,15.0|3,2.0|4,17.0|5,20.0|, _tag=0.0], NodeInfo [_key=2, _info=|1,15.0|, _tag=0.0], NodeInfo [_key=3, _info=|1,2.0|5,4.0|, _tag=0.0], NodeInfo [_key=4, _info=|1,17.0|5,1.0|, _tag=0.0], NodeInfo [_key=5, _info=|1,20.0|3,4.0|4,1.0|, _tag=0.0]]");

	}


}
