package logger;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
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
	public Graph GetAnyGraph() throws InterruptedException, ExecutionException
	{
		return GetGraph(5,4);
	}
	
	public static Graph<Integer, Integer> GetGraph(int NodeNum,int EdgeNum) throws InterruptedException, ExecutionException {
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
    	ExecutorService executor = ForkJoinPool.commonPool();
    	final Future<ArrayList<Node>> NodeCreator =
    			executor.submit(new Callable<ArrayList<Node>>() {
            public ArrayList<Node> call() {
            ArrayList<Node> listN = new ArrayList<Node>();
    		System.out.println("nodes calcutations started");
            	for(int i = 0;i<NodeNum;i++)
            	{
            		listN.add(GetNode(sName));
            	}
        		System.out.println("nodes calcutations finished");
                return listN;
            }});
    	final Future<ArrayList<Edge>> EdgeCreator =
    			executor.submit(new Callable<ArrayList<Edge>>() {
    	            public ArrayList<Edge> call() throws InterruptedException, ExecutionException {
        	    	System.out.println("edges calcutations started");
    	            ArrayList<Edge> listE = new ArrayList<Edge>();
    	            ArrayList<Node> nodeList = NodeCreator.get();
    	        	for(int i = 0;i<EdgeNum;i++)
    	        	{
    	        		listE.add(GetEdge(fNode, nodeList, sName));
    	        	}
    	    		System.out.println("edges calcutations finished");
    	                return listE;
    	            }});
    	System.out.println("graph calc started");
    	Graph<Integer,Integer> result = new Graph<Integer,Integer>(NodeCreator.get(), EdgeCreator.get(), sName.get(), sName.get());
    	System.out.println("graph calc finished");
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
