package logger;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.company.*;

@Configuration
@Component
@ComponentScan("com.company")
@ComponentScan("logger")
public class ApplicationConfig {
	
	@Bean
	public Graph GetAnyGraph()
	{
		return GetGraph(5,4);
	}
	
//	@Bean
//	@Scope("singleton")
	public static Graph<Integer, Integer> GetGraph(int NodeNum,int EdgeNum) {
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
    		listN.add(GetNode(sName));
    	}
    	for(int i = 0;i<EdgeNum;i++)
    	{
    		listE.add(GetEdge(fNode, listN, sName));
    	}
    	Graph<Integer,Integer> result = new Graph<Integer,Integer>(listN, listE, sName.get(), sName.get());
    	return result;
    }
	
	@Bean
	@Scope("prototype")
    public static Node GetNode(Supplier<String> NiveNameCreator) {
    	Node result = new Node(NiveNameCreator.get(),NiveNameCreator.get());
    	return result;
    }
	
	@Bean
	@Scope("prototype")
    public static Edge GetEdge(Function<ArrayList<Node>,Pair<Node,Node>> NiveCreator,ArrayList<Node> nodes,Supplier<String> NiveNameCreator) {
    	Pair<Node,Node> result = NiveCreator.apply(nodes);
    	return new Edge(NiveNameCreator.get(),NiveNameCreator.get(),result.getFirst(),result.getSecond());
    }
	
	@Bean
	@Scope("prototype")
	public String GetIncMtx(Graph graph) throws Exception {
		IncidenceMatrix mtx = new IncidenceMatrix(graph);
		mtx.CreateMatrix();
		return mtx.getContent();
	}
	
	@Bean
	@Scope("prototype")
	public String GetAdjMtx(Graph graph) throws Exception {
		AdjacencyMatrix mtx = new AdjacencyMatrix(graph);
		mtx.CreateMatrix();
		return mtx.getContent();
	}
	
}
