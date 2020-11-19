package ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WGraph_DSTest {

	@Test
	void testWGraph_DS() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNode() {
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		g.connect(0, 1, 5);
		g.connect(0, 2, 7);
		node_info n = g.getNode(0);
		assertEquals(n.toString(),"NodeInfo [_key=0, _info=|1,5.0|2,7.0|, _tag=0.0]");
		node_info n2 = g.getNode(3);
		assertNull(n2);

	}

	@Test
	void testHasEdge() {
		weighted_graph g = new WGraph_DS();
		g.addNode(400);
		g.addNode(125);
		g.addNode(8);
		g.addNode(17);

		g.connect(17, 125, 52.784);
		g.connect(17, 50, 7000);
		g.connect(17, 17, 0);

		assertTrue(g.hasEdge(125, 17));
		assertFalse(g.hasEdge(17, 50));
		assertFalse(g.hasEdge(17, 8));
		assertFalse(g.hasEdge(17, 17));


	}

	@Test
	void testGetEdge() {

		weighted_graph g = new WGraph_DS();

		g.addNode(125);
		g.addNode(8);
		g.addNode(17);

		g.connect(17, 125, 52.784);
		g.connect(17, 50, 7000);
		g.connect(17, 17, 0);

		assertEquals(g.getEdge(17, 125), 52.784);
		assertEquals(g.getEdge(17, 50), -1);
		assertEquals(g.getEdge(17, 8), -1);


	}

	@Test
	void testAddNode() {

		weighted_graph g = new WGraph_DS();

		g.addNode(125);
		g.addNode(8);
		g.addNode(170000);

		g.connect(8, 170000, 45);
		g.connect(170000, 125, 2);

		assertEquals(g.getV().toString(), "[NodeInfo [_key=170000, _info=|8,45.0|125,2.0|, _tag=0.0], NodeInfo [_key=8, _info=|170000,45.0|, _tag=0.0], NodeInfo [_key=125, _info=|170000,2.0|, _tag=0.0]]");
		g.addNode(8);
		assertEquals(g.getV().toString(), "[NodeInfo [_key=170000, _info=|8,45.0|125,2.0|, _tag=0.0], NodeInfo [_key=8, _info=|170000,45.0|, _tag=0.0], NodeInfo [_key=125, _info=|170000,2.0|, _tag=0.0]]");

	}

	@Test
	void testConnect() {

		weighted_graph g = new WGraph_DS();

		g.addNode(125);
		g.addNode(8);
		g.addNode(170000);

		g.connect(8, 170000, 45);
		g.connect(170000, 125, 2);
		g.connect(8, 8, 7);
		g.connect(50, 51, 9);

		assertTrue(g.hasEdge(170000, 8));
		assertFalse(g.hasEdge(8, 8));
		assertFalse(g.hasEdge(50, 51));




	}

	@Test
	void testGetV() {

		weighted_graph g = new WGraph_DS();

		assertEquals(g.getV().toString(), "[]");

		g.addNode(125);
		g.addNode(8);
		g.addNode(170000);

		g.connect(8, 170000, 45);
		g.connect(170000, 125, 2);


		assertEquals(g.getV().toString(), "[NodeInfo [_key=170000, _info=|8,45.0|125,2.0|, _tag=0.0], NodeInfo [_key=8, _info=|170000,45.0|, _tag=0.0], NodeInfo [_key=125, _info=|170000,2.0|, _tag=0.0]]");



	}

	@Test
	void testGetVInt() {

		weighted_graph g = new WGraph_DS();

		g.addNode(125);
		g.addNode(8);
		g.addNode(5);

		g.connect(8, 5, 45);
		g.connect(5, 125, 2);


		assertEquals(g.getV(5).toString(), "[NodeInfo [_key=8, _info=|5,45.0|, _tag=0.0], NodeInfo [_key=125, _info=|5,2.0|, _tag=0.0]]");
		assertNull(g.getNode(59));
	}

	@Test
	void testRemoveNode() {

		weighted_graph g = new WGraph_DS();

		g.addNode(125);
		g.addNode(8);
		g.addNode(5);

		g.connect(8, 5, 45);
		g.connect(5, 125, 2);
		g.connect(8, 125, 0.25);

		g.removeNode(5);


		assertEquals(g.getNode(8).toString(), "NodeInfo [_key=8, _info=|125,0.25|, _tag=0.0]");
		assertEquals(g.getNode(125).toString(), "NodeInfo [_key=125, _info=|8,0.25|, _tag=0.0]");


	}

	@Test
	void testRemoveEdge() {

		weighted_graph g = new WGraph_DS();

		g.addNode(125);
		g.addNode(8);
		g.addNode(5);

		g.connect(8, 5, 45);
		g.connect(5, 125, 2);
		g.connect(8, 125, 0.25);

		g.removeEdge(8, 5);
		g.removeEdge(8, 5);
		g.removeEdge(8, 6);

		assertEquals(g.getV(5).toString(), "[NodeInfo [_key=125, _info=|5,2.0|8,0.25|, _tag=0.0]]");
		assertEquals(g.getV(8).toString(), "[NodeInfo [_key=125, _info=|5,2.0|8,0.25|, _tag=0.0]]");


	}

	@Test
	void testNodeSize() {
		fail("Not yet implemented");
	}

	@Test
	void testEdgeSize() {
		weighted_graph g = new WGraph_DS();

		g.addNode(125);
		g.addNode(8);
		g.addNode(5);
		g.addNode(38);
		g.addNode(45);
		

		g.connect(8, 5, 45);
		g.connect(5, 125, 2);
		g.connect(8, 125, 0.25);
		g.connect(45, 5, 4);
		g.connect(38, 5, 3);

		g.removeEdge(8, 5);
		g.removeEdge(8, 5);
		g.removeEdge(8, 6);
		g.removeNode(5);
		
		assertEquals(g.edgeSize(), 1);
	}



}
