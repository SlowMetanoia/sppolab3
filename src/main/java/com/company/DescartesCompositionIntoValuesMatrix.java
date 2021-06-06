package com.company;

import java.util.*;
import java.util.function.Function;

public class DescartesCompositionIntoValuesMatrix<T1,T2,ValuesT> implements IPrintable{
    protected final List<T1> params1;
    protected final List<T2> params2;
    protected ValuesT[][] Matrix;
    protected Function<Pair<T1,T2>,ValuesT> Map;
    protected String content = new String();

    public void setMap(Function<Pair<T1, T2>, ValuesT> map) {
        Map = map;
    }

    public DescartesCompositionIntoValuesMatrix(List<T1> params1, List<T2> params2) {
        this.params1 = params1;
        this.params2 = params2;
        content = new String("");
    }

    public ValuesT[][] CreateMatrix() throws Exception {
        if(Map == null)
        {
            throw new Exception("Relation function must not be null");
        }
        for (int i = 0; i < params1.size(); i++) {
            for (int j = 0; j < params2.size(); j++) {
                //Matrix[i][j] = Map.apply(new Pair<>(params1.get(i),params2.get(j)));
                content = content + Map.apply(new Pair<>(params1.get(i),params2.get(j))).toString() + "\t";
            }
            content = content + "\n";
        }
        return Matrix;
    }
    
    @Override
    public String getContent() {
        return content;
    }
}