package com.company;

import java.util.ArrayList;
import java.util.Arrays;
//билдер для графа
public class GraphBuilder extends AbstractBuilder<Graph>{
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private String name;
    private String description;
    private ArrayList<Graph> created;

    public GraphBuilder(){
        name = null;
        description = null;
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
        created = new ArrayList<>();
    }
    GraphBuilder(Graph graph)
    {
    	this.nodes =graph.getNodes();
    	this.edges =graph.getEdges(); 
        if((name == null)&&(description == null))
        {
            this.name = graph.getName();
            this.description = graph.getDescription();
        }
        created = new ArrayList<>();
    }
    public void addNodes(ArrayList<Node> nodes) {
        for (Node node:nodes)
            if(node!=null)
                this.nodes.add(node);
    }
    public  void addNode(Node node){
        this.nodes.add(node);
    }
    public  void addEdge(Edge edge){
        this.edges.add(edge);
    }
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Graph> getCreated() {
        return created;
    }

    public void addEdges(ArrayList<Edge> edges) {
        for (Edge edge:edges) this.edges.add(edge);
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Graph build() {
        Graph graph = new Graph(nodes, edges, name, description);
        for (Node node:nodes) {
            node.setGraph(graph);
        }
        for (Edge edge: edges) {
            edge.setGraph(graph);
        }
        created.add(graph);
        return graph;
    }
}