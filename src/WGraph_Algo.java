package ex1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;




public class WGraph_Algo implements weighted_graph_algorithms {

	private weighted_graph wga;
	public static double edge;
	public static String info = "";

	/**
	 * This function takes a graph parameter (g) and performs an assignment to the object variable (wga).
	 * O(1)
	 */
	@Override
	public void init(weighted_graph g) {
		this.wga = g;

	}
	/**
	 * O(1)
	 */
	@Override
	public weighted_graph getGraph() {

		return this.wga;
	}

	/**
	 * This function performs deep copying between two graphs
	 * O(n)
	 */
	@Override
	public weighted_graph copy() {
		weighted_graph ans = new WGraph_DS();
		Collection<node_info> arr = wga.getV();
		for(node_info n : arr) {
			edge = (wga.getV(n.getKey()).size())/2.0;
			info = n.getInfo();
			ans.addNode(n.getKey());
			info = "";
			edge = 0;

		}
		return ans;
	}

	/**
	 * This function returns true if there is a Route between all the vertices in the graph, otherwise returns false.
	 * O(V*E)
	 */
	@Override
	public boolean isConnected() {
		
		if(wga.getV().size()==0) return true;
		Iterator<node_info> temp = wga.getV().iterator();
		node_info n = temp.next();
		Queue<node_info> q = new LinkedBlockingQueue<node_info>();
		q.add(n);
		n.setTag(1);
		while(!q.isEmpty()) {
			node_info v = q.poll();
			Collection<node_info> arrNei = wga.getV(v.getKey());
			for(node_info u : arrNei) {
				if(u.getTag() == 0) {
					u.setTag(1);
					q.add(u);
				}
			}
			v.setTag(2);
		}
		Collection<node_info> col = wga.getV();
		for(node_info n1 : col) {
			if(n1.getTag() != 2) {
				for(node_info w : wga.getV()) {
					w.setTag(0);
				}
				return false;
			}
		}
		for(node_info w : wga.getV()) {
			w.setTag(0);
		}
		return true;
	}


	/**
	 * This function receives keys of 2 vertices in the graph, 
	 * Checking what is the shortest route between them, And returns the number of steps in this route.
	 * O(V*E)
	 */
	@Override
	public double shortestPathDist(int src, int dest) {

		for(node_info vertex : wga.getV()) {
			vertex.setTag(Integer.MAX_VALUE);
		}
		node_info start = wga.getNode(src);
		start.setTag(0);
		PriorityBlockingQueue<node_info> qp = new PriorityBlockingQueue<node_info>(wga.getV().size(),new NodeComp());
		qp.put(start);
		while(!qp.isEmpty()) {
			node_info v = qp.poll();
			for(node_info nei : wga.getV(v.getKey())) {
				double newSumPath =  v.getTag()+wga.getEdge(v.getKey(), nei.getKey());
				if(newSumPath < nei.getTag()) {
					nei.setTag(newSumPath);
					qp.remove(nei);
					qp.add(nei);
				}
			}
		}
		return wga.getNode(dest).getTag();
	}

	/**
	 * This function receives keys of 2 vertices in the graph, 
	 * Checking what is the shortest route between them,
	 * And returns a list of all the vertices we went through in this route.
	 * O(V*E)
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {

		for(node_info vertex : wga.getV()) {
			vertex.setTag(Integer.MAX_VALUE);
		}
		int[] prev = new int[wga.nodeSize()];
		node_info start = wga.getNode(src);
		start.setTag(0);
		PriorityBlockingQueue<node_info> qp = new PriorityBlockingQueue<node_info>(wga.getV().size(),new NodeComp());
		qp.add(start);
		prev[src%wga.nodeSize()] = -1;
		while(!qp.isEmpty()) {
			node_info v = qp.poll();
			Collection<node_info> arrNei = wga.getV(v.getKey());
			for(node_info u : arrNei) {
				double sumNewPath = v.getTag()+wga.getEdge(v.getKey(), u.getKey());
				if(sumNewPath < u.getTag()) {
					u.setTag(sumNewPath);
					qp.remove(u);
					qp.add(u);
					prev[u.getKey()%wga.nodeSize()] = v.getKey();
				}
			}
		}
		List<node_info> ans = new ArrayList<node_info>();
		int run = dest;
		while(run != src) {
			ans.add(0,wga.getNode(run));
			run = prev[run%wga.nodeSize()];
		}
		ans.add(0, wga.getNode(src));
		for(node_info w : wga.getV()) {
			w.setTag(0);
		}
		return ans;
	}


	/**
	 * Saves this weighted graph to the given file name.
	 * O(n)
	 */
	@Override
	public boolean save(String file) {

		File f1 = new File(file);
		try {
			FileWriter writer = new FileWriter(f1);
			String ans = "graph:\n";
			for(node_info v : wga.getV()) {
				ans += ""+v.getKey()+": "+v.getInfo()+"\n";
			}
			writer.write(ans); 
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * This function load a graph to this graph algorithm.
	 * O(n)
	 */
	@Override
	public boolean load(String file) {
		String str;
		File f1 = new File(file);
		try {
			BufferedReader br = new BufferedReader(new FileReader(f1));
			while((str = br.readLine()) != null) {
				if(!str.equals("graph:")) {
					int i = str.indexOf("|");
					int key = Integer.parseInt(str.substring(0, i-2));
					info = str.substring(i);
					wga.removeNode(key);
					wga.addNode(key);
					info = "";
				}
			}
			br.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			return false;
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

}
