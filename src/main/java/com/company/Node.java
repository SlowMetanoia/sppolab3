package com.company;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//вершина графа
public class Node implements IConnectable, IShortNameable {

    private Graph graph;
    final private String name;
    final private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Node(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public String toString() {
        return "\n Node{" +
                "\t name='" + name + 
                "\t description='" + description + 
                '}';
    }

    @Override
    public Boolean IsNodeConnected(Node node) {
        final ArrayList<Edge> edges = graph.getEdges();
        for (Edge edge: edges) {
            if(edge.isSource(this)&&edge.isReceiver(node)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Boolean IsEdgeConnected(Edge edge) {
        return edge.isMember(this);
    }

    @Override
    public String ShortName() {
        return name + description;
    }
}
