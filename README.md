# Ex1-OOP


In this project we create a unidirectional weighted graph.
the project contains WGraph_DS A class that implements the weighted_graph interface,
and contains the internal class NodeInfo that implements the node_info interface.
in addition we have the WGraph_Algo class which implements the weighted_graph_algorithms interface.


## WGraph_DS class:
This class represents an undirectional weighted graph.
using an internal class NodeInfo that represents the vertices in the graph.

* internal class NodeInfo:
Represents a vertex in a graph with a unique key,
information about the neighbors of the vertex and the weight of its edges.

In the WGraph_DS class we will use the following functions:


* public node_info getNode(int key)
  This function gets a key that represents a vertex in the graph     
 ,Checks if the vertex is contained in the graph, if so - returns it
  otherwise - returns null.


* public boolean hasEdge(int node1, int node2)
 This function receives two keys that represent vertices in the graph
Checks if there is a side between them, if so - returns true
otherwise - returns false.


* public double getEdge(int node1, int node2)
This function receives two vertex keys and returns the distance representing the edge.


* public void addNode(int key)
This function gets a new unique key and adds it to the graph.


* public void connect(int node1, int node2, double w)
This function gets two keys of vertices, and a distance w.
This function connects the edge between the two vertices if they exist in the graph,
And adds to the edge the weight.


* public Collection<node_info> getV()
This function returns all the vertices in the graph


* public Collection<node_info> getV(int node_id)
This function receives a key that represents a vertex and returns all the neighbors of that vertex.


* public node_info removeNode(int key)
This function removes a vertex from the graph, pass the list of the neighbors of the key, deletes them from the vertex
And returns the deleted vertex.


* public void removeEdge(int node1, int node2)
This function receives two keys that represent vertices in the graph - if they are in the graph and there is an edge between them, then the function deletes the edge.


* public int nodeSize()
This function returns the number of all vertices in the graph.


* public int edgeSize()
This function returns the number of all edges in the graph.


* public int getMC() 
This function returns the number of all actions performed on the graph.




## WGraph_Algo class:

In the WGraph_Algo class we will use the following functions:


* public void init(weighted_graph g)
This function takes a graph parameter (g) and performs an assignment to the object variable (wga).


* public weighted_graph copy()
This function performs deep copying between two graphs.


* public boolean isConnected()
This function returns true if there is a Route between all the vertices in the graph, otherwise returns false.


* public double shortestPathDist(int src, int dest)
This function receives keys of 2 vertices in the graph, Checking what is the shortest route between them, And returns the number of steps in this route.


* public List<node_info> shortestPath(int src, int dest)
This function receives keys of 2 vertices in the graph, 
Checking what is the shortest route between them,And returns a list of all the vertices we went through in this route.


* public boolean save(String file)
 Saves this weighted graph to the given file name.


* public boolean load(String file)
This function load a graph to this graph algorithm.
















