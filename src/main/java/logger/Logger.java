package logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
//возможно, аоп логгер
@Component
@Aspect
@EnableAspectJAutoProxy 
public class Logger {
	@Pointcut("execution(* com.company.*.*(..))||execution(* logger.*.*(..))")
	public void allMeth() {}
	
	@Before("allMeth()")
	public void beforeLog(JoinPoint jp){
		System.out.println(jp.getSignature()+" started it's work");
	}
	@AfterReturning("allMeth()")
	public void afterLog(JoinPoint jp){
		System.out.println(jp.getSignature()+" ended it's work");
	}
}
