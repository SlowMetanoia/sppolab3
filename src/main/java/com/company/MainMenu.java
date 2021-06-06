package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class MainMenu extends Menu<GraphBuilder> {
    public MainMenu(GraphBuilder context) {
        super(context);
        AddOption("AddNode","\t Input signature: \"name\" \"description\" (name must be unique)", (GraphBuilder graphbuilder)->{
            Scanner input = new Scanner(System.in);
            String[] strings = input.nextLine().split(" ");
            graphbuilder.addNode(new Node(strings[0], strings[1]));
        });
        AddOption("AddEdge", "\t Input signature: \"name\" \"description\"", (GraphBuilder graphbuilder)->{
            Scanner input = new Scanner(System.in);
            String[] strings = input.nextLine().split(" ");
            System.out.println("Choose Two Nodes");
            Pair<Node, Node> nodes = MainMenu.ChooseTwoNodes(graphbuilder);
            graphbuilder.addEdge(new Edge(strings[0], strings[1], nodes.getFirst(), nodes.getSecond()));
        });
        AddOption("BuildGraph", " ", (GraphBuilder graphbuilder)->{
            Scanner input = new Scanner(System.in);
            String[] strings = input.nextLine().split(" ");
            graphbuilder.setName(strings[0]);
            graphbuilder.setDescription(strings[1]);
            graphbuilder.build();
        });
    }
    static public Pair<Node, Node> ChooseTwoNodes(GraphBuilder graphBuilder) {
        HashMap<String, Node> nodesMap = new HashMap<>();
        System.out.println("Node names:");
        for (Node node: graphBuilder.getNodes()) {
            nodesMap.put(node.ShortName(), node);
            System.out.println(node.ShortName());
        }
        Scanner input = new Scanner(System.in);
        String[] strings = input.nextLine().split(" ");
        return new Pair<>(nodesMap.get(strings[0]),nodesMap.get(strings[1]));
    }

}
