package com.company;

import java.util.List;

public class IncidenceMatrix extends DescartesCompositionIntoValuesMatrix<Edge,Node,Integer>{
    public IncidenceMatrix(List<Edge> params1, List<Node> params2) {
        super(params1, params2);
        this.Map =(Pair<Edge,Node> context)->{
            Node node = context.getSecond();
            Edge edge = context.getFirst();
            if (edge.isSource(node))
                return 1;
            if (edge.isReceiver(node))
                return -1;
            return 0;
        };
        content = content + "IncidenceMatrix: \n";
    }
}
