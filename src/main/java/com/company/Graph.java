package com.company;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.Random;

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
    public static Graph<Integer,Integer> GetGraph(int NodeNum,int EdgeNum) {
    	Function<ArrayList<Node>,Pair<Node,Node>> fNode = (ArrayList<Node> list)->
    	{
    		Random rand = new Random();
    		int l = list.toArray().length;
    		Pair<Node,Node> result = new Pair<Node,Node>(list.get(rand.nextInt(l)),list.get(rand.nextInt(l)));
    		return result;
    	};
    	Supplier<String> sName = ()->{
    		Random rand = new Random();
    		Integer result = rand.nextInt(1000);
    		return result.toString();
    	};
    	ArrayList<Node> listN = new ArrayList<Node>();
    	ArrayList<Edge> listE = new ArrayList<Edge>();
    	for(int i = 0;i<NodeNum;i++)
    	{
    		listN.add(Node.GetNode(sName));
    	}
    	for(int i = 0;i<EdgeNum;i++)
    	{
    		listE.add(Edge.GetEdge(fNode, listN, sName));
    	}
    	Graph<Integer,Integer> result = new Graph<Integer,Integer>(listN, listE, sName.get(), sName.get());
    	return result;
    }
    @Override
    public String toString() {
    	return "nodes = "+nodes.toString()+"\n edges = "+edges.toString()+"\n name = "+name+"\n description = "+ description;
    }
}
