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

public node_info getNode(int key)
- This function gets a key that represents a vertex in the graph     
,Checks if the vertex is contained in the graph, if so - returns it
otherwise - returns null.

public boolean hasEdge(int node1, int node2)
- This function receives two keys that represent vertices in the graph
Checks if there is a side between them, if so - returns true
otherwise - returns false.


