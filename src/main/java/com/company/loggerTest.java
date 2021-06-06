package com.company;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import logger.Logger;

@Component("loggerTest")
public class loggerTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
	       		 new AnnotationConfigApplicationContext(logger.ApplicationConfig.class);
		loggerTest LoggerTest = ctx.getBean("loggerTest",loggerTest.class);
		ctx.close();
		LoggerTest.saySorry();
	}
	public void saySorry() {
		System.out.println("I am sorry...");
	}
}
