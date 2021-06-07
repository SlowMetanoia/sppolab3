package com.company;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.Random;

//собственно граф
public class Graph<NodeT,EdgeT> {

    final private ArrayList<Node> nodes;
    final private ArrayList<Edge> edges;
    final private String name;
    final private String description;

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges, String name, String description) {
        this.nodes = nodes;
        this.edges = edges;
        this.name = name;
        this.description = description;
    }

    public ArrayList<Node> filterNodes(Function<Node,Boolean> filter)
    {
        ArrayList<Node> nodeList = new ArrayList<>();
        for (Node node: this.nodes) {
            if(filter.apply(node))
                nodeList.add(node);
        }
        return nodeList;
    }

    public ArrayList<Edge> filterEdges(Function<Edge,Boolean> filter)
    {
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (Edge edge:edges) {
            if (filter.apply(edge))
                edgeList.add(edge);
        }
        return edgeList;
    }
    
    @Override
    public String toString() {
    	return "nodes = "+nodes.toString()+"\n edges = "+edges.toString()+"\n name = "+name+"\n description = "+ description;
    }
}
