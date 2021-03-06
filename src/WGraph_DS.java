package ex1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;



/**
 * WGraph_DS A class that implements the weighted_graph interface 
 * and contains the internal class NodeInfo that implements the node_info interface.
 * This class represents an undirectional weighted graph.
 * 
 * 
 * @author ���
 *
 */
public class WGraph_DS implements weighted_graph {

	private HashMap<Integer, node_info> gd;
	private static int count = 0;
	private int numOfEdge = 0;
	private int statusCount = 0;


	public WGraph_DS() {

		this.gd = new HashMap<Integer, node_info>();
	}


	/**
	 * internal class NodeInfo.
	 * Represents a vertex in a graph with a unique key,
	 * information about the neighbors of the vertex and the weight of its edges.
	 * 
	 * @author ���
	 *
	 */
	private class NodeInfo implements node_info {

		private int _key;
		private String _info;
		private double _tag;

		/**
		 * constructor
		 */
		public NodeInfo() {

			this._key = count++;
			this._tag = 0;
			this._info = "";
		}

		/**
		 * constructor 
		 * @param key
		 */
		public NodeInfo(int key) {

			this._key = key;
			this._tag = 0;
			this._info = "";
		}



		@Override
		public int getKey() {

			return this._key;
		}

		@Override
		public String getInfo() {

			return this._info;
		}

		@Override
		public void setInfo(String s) {

			this._info = s+"";
		}

		@Override
		public double getTag() {

			return this._tag;
		}

		@Override
		public void setTag(double t) {

			this._tag = t;
		}

		@Override
		public String toString() {
			return "NodeInfo [_key=" + _key + ", _info=" + _info + ", _tag=" + _tag + "]";
		}
	}



	/**
	 * This function gets a key that represents a vertex in the graph,
	 * Checks if the vertex is contained in the graph, if so - returns it
	 * otherwise - returns null.
	 * O(1) - HashMap
	 */
	@Override
	public node_info getNode(int key) {

		if(gd.containsKey(key)) {
			return this.gd.get(key);
		}
		return null;
	}


	/**
	 * This function receives two keys that represent vertices in the graph,
	 * Checks if there is a side between them, if so - returns true
	 * otherwise - returns false.
	 * O(k) - k is number of degrees of the vertex(node1).
	 */
	@Override
	public boolean hasEdge(int node1, int node2) {
		if(gd.containsKey(node1) && gd.containsKey(node2) ) {
			Collection<node_info> check = this.getV(node1);
			if(check != null) {
				for(node_info run : check) {
					if(run.getKey() == node2) {
						return true;
					}
				}
			}
		}
		return false;
	}


	/**
	 * This function receives two vertex keys and returns the distance representing the edge.
	 * O(1) - Find the neighbor string of n1 the vertex n2 and return the weight for it.
	 */
	@Override
	public double getEdge(int node1, int node2) {

		if(this.hasEdge(node1, node2)) {
			int i = this.getNode(node1).getInfo().indexOf("|"+node2+",");
			String ans = "";
			boolean flag = false;
			String nei = this.getNode(node1).getInfo();
			for(int ii = i+1 ; nei.charAt(ii) != '|' ; ii++) {

				if(flag == true) {
					ans += nei.charAt(ii);
				}
				if(nei.charAt(ii) == ',') {
					flag= true;
				}
			}
			return Double.parseDouble(ans);

		}
		return -1;
	}


	/**
	 * This function gets a new unique key and adds it to the graph.
	 * O(1)
	 */
	@Override
	public void addNode(int key) {

		if(!gd.containsKey(key)) {
			node_info n = new NodeInfo(key);
			if(WGraph_Algo.info != "") {
				n.setInfo(WGraph_Algo.info);
			}
			this.gd.put(key,n);
			statusCount++;
		}
	}

	/**
	 * This function gets two keys of vertices, and a distance w.
	 * This function connects the edge between the two vertices if they exist in the graph,
	 * And adds to the edge the weight.
	 * O(1)
	 */
	@Override
	public void connect(int node1, int node2, double w) {

		if(gd.containsKey(node1) && gd.containsKey(node2) && !this.hasEdge(node1, node2) && node1 != node2 ) {
			node_info n1 = this.getNode(node1);
			if(n1.getInfo() == "") {
				n1.setInfo("|"+node2+","+w+"|");	
			}
			else {
				n1.setInfo(n1.getInfo()+node2+","+w+"|");
			}
			node_info n2 = this.getNode(node2);
			if(n2.getInfo() == "") {
				n2.setInfo("|"+node1+","+w+"|");	
			}
			else {
				n2.setInfo(n2.getInfo()+node1+","+w+"|");
			}
			numOfEdge++;
			statusCount++;
		}
	}


	/**
	 * This function returns all the vertices in the graph.
	 * O(1)
	 */
	@Override
	public Collection<node_info> getV() {

		return gd.values();
	}


	/**
	 * This function receives a key that represents a vertex and returns all the neighbors of that vertex.
	 *  O(k) - k is number of degrees of the vertex(node_id).
	 */
	@Override
	public Collection<node_info> getV(int node_id) {

		if(gd.containsKey(node_id)) {
			Collection<node_info> ans = new ArrayList<node_info>();
			StringTokenizer st = new StringTokenizer(this.getNode(node_id).getInfo(), "|,");
			for (int i = 0; st.hasMoreTokens() ; i++) {
				if(i%2 == 0) {
					ans.add(this.getNode(Integer.parseInt(st.nextToken())));
				}
				else {
					st.nextToken();
				}

			}
			return ans;
		}
		return null;
	}


	/**
	 * This function removes a vertex from the graph ,
	 * pass the list of the neighbors of the key, deletes them from the vertex,
	 * and returns the deleted vertex.
	 *  O(k) - k is number of degrees of the vertex(key).
	 */
	@Override
	public node_info removeNode(int key) {
		if(gd.containsKey(key)) {
			Collection<node_info> die = this.getV(key);
			for(node_info nei : die) {
				String info = nei.getInfo();
				int start = info.indexOf("|"+key+",");
				int end = info.indexOf("|", start+1);
				if(start == 0 && end == info.length()-1) {
					nei.setInfo("");
				}
				else {
					nei.setInfo(info.substring(0, start)+info.substring(end));
				}
			}
			statusCount++;
			numOfEdge -= die.size();
			return gd.remove(key);
		}
		return null;
	}


	/**
	 * This function receives two keys that represent vertices in the graph - if they are in the graph and there is an edge between them, 
	 * then the function deletes the edge.
	 */
	@Override
	public void removeEdge(int node1, int node2) {
		if( this.hasEdge(node1, node2) ) {
			node_info n1 = this.getNode(node1);
			String info1 = n1.getInfo();
			int start1 = info1.indexOf("|"+node2+",");
			int end1 = info1.indexOf("|", start1+1);
			if(start1 == 0 && end1 == info1.length()-1) {
				n1.setInfo("");
			}
			else {
				n1.setInfo(info1.substring(0, start1)+info1.substring(end1));
			}


			node_info n2 = this.getNode(node2);
			String info2 = n2.getInfo();
			int start2 = info2.indexOf("|"+node1+",");
			int end2 = info2.indexOf("|", start2+1);
			if(start2 == 0 && end2 == info2.length()-1) {
				n2.setInfo("");
			}
			else {
				n2.setInfo(info2.substring(0, start2)+info2.substring(end2));
			}
			numOfEdge--;
			statusCount++;
		}
	}


	/**
	 * This function returns the number of all vertices in the graph.
	 */
	@Override
	public int nodeSize() {

		return gd.size();
	}


	/**
	 * This function returns the number of all edges in the graph.
	 */
	@Override
	public int edgeSize() {

		return numOfEdge;
	}


	/**
	 * This function returns the number of all actions performed on the graph.
	 */
	@Override
	public int getMC() {

		return statusCount;
	}

}



