package com.company;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

public class Edge implements IConnectable{

    private Graph graph;
    final private String name;
    final private String description;
    final private Node source;
    final private Node receiver;

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Node getSource() {
        return source;
    }

    public Node getReceiver() {
        return receiver;
    }

    public Edge(String name, String description, Node source, Node receiver) {
        this.name = name;
        this.description = description;
        this.source = source;
        this.receiver = receiver;
    }

    boolean isMember(Node node) {return node == source||node == receiver;}
    boolean isSource(Node node) {return node == source;}
    boolean isReceiver(Node node) {return node == receiver;}

    @Override
    public Boolean IsNodeConnected(Node node) {
        return isMember(node);
    }

    @Override
    public Boolean IsEdgeConnected(Edge edge) {
        return edge.isMember(receiver);
    }
    public static Edge GetEdge(Function<ArrayList<Node>,Pair<Node,Node>> NiveCreator,ArrayList<Node> nodes,Supplier<String> NiveNameCreator) {
    	Pair<Node,Node> result = NiveCreator.apply(nodes);
    	return new Edge(NiveNameCreator.get(),NiveNameCreator.get(),result.getFirst(),result.getSecond());
    }
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "\n source = "+source.toString() + "\n receiver = "+ receiver.toString()+"\n name = "+name +"\n description = "+ description+"\n-------------------";
    }
}
