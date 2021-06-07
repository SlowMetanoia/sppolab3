package com.company;

import java.util.List;
//Матрица смежности
public class AdjacencyMatrix extends DescartesCompositionIntoValuesMatrix<Node,Node,Integer>{
    public AdjacencyMatrix(List<Node> params1, List<Node> params2) {
        super(params1, params2);
        Map = (Pair<Node,Node> pair)->{
            Node node1 = pair.getFirst();
            Node node2 = pair.getSecond();
            if(node1.IsNodeConnected(node2))
                return 1;
            else
                return 0;
        };
        content = content + "AdjacencyMatrix: \n";
    }
    public AdjacencyMatrix(Graph graph) {
    	super(graph.getNodes(), graph.getNodes());
    	Map = (Pair<Node,Node> pair)->{
            Node node1 = pair.getFirst();
            Node node2 = pair.getSecond();
            if(node1.IsNodeConnected(node2))
                return 1;
            else
                return 0;
        };
        content = content + "AdjacencyMatrix: \n";
    }
}
