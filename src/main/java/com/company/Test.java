package com.company;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import logger.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Test {
    public static void main() throws Exception {
        GraphBuilder graphBuilder = new GraphBuilder(scanGraph("ApplicationContext.xml","Graph1"));	
        System.out.println("Здесь должен запускаться логгер");
        AnnotationConfigApplicationContext ctx = 
       		 new AnnotationConfigApplicationContext(config.class);
        MainMenu mainMenu = new MainMenu(graphBuilder);
        while(graphBuilder.getCreated().size() == 0){
            System.out.println(mainMenu.getContent());
            mainMenu.Read();
        }
        Graph graph = graphBuilder.getCreated().get(0);
        //System.out.println(graph.toString());
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(graph.getNodes(), graph.getNodes());
        IncidenceMatrix incidenceMatrix = new IncidenceMatrix(graph.getEdges(), graph.getNodes());
        adjacencyMatrix.CreateMatrix();
        incidenceMatrix.CreateMatrix();
        System.out.println(adjacencyMatrix.getContent());
        System.out.println(incidenceMatrix.getContent());
        ctx.close();
    }
    //------------------------------------------------------------------------
    private static Graph scanGraph(String path,String graphBeanName) 
    {
    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(path);
    	System.out.println(ctx.getBean(graphBeanName.toString()));
    	Graph result = ctx.getBean(graphBeanName,Graph.class);
    	ctx.close();
    	return result;
    }
}
