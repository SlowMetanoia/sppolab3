package com.company;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import logger.ApplicationConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Test {
    public static void main() throws Exception {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        GraphBuilder graphBuilder = new GraphBuilder(context.getBean("GetAnyGraph",Graph.class));	
        
        MainMenu mainMenu = new MainMenu(graphBuilder);
        while(graphBuilder.getCreated().size() == 0){
            System.out.println(mainMenu.getContent());
            mainMenu.Read();
        }
        Graph graph = graphBuilder.getCreated().get(0);
        System.out.println(graph.toString());
        String adjacencyMatrix = context.getBean("GetAdjMtx",String.class);
        String incidenceMatrix = context.getBean("GetIncMtx",String.class);
        //AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(graph.getNodes(), graph.getNodes());
        //IncidenceMatrix incidenceMatrix = new IncidenceMatrix(graph.getEdges(), graph.getNodes());
        //adjacencyMatrix.CreateMatrix();
        //incidenceMatrix.CreateMatrix();
        System.out.println(adjacencyMatrix);
        System.out.println(incidenceMatrix);
    }
    //------------------------------------------------------------------------
}
