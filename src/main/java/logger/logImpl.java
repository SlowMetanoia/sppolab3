package logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy     
public class logImpl {
	@Before("execution(* com.company.*.*(..))")
    public void calcStartInfo(JoinPoint jp) {
    	System.out.println(""+jp.getSignature());
    }
	
	@Around("execution(* com.company.*.*(..))")
	public Object checkExecutionTime(ProceedingJoinPoint jp) throws Throwable{		
		long start = System.currentTimeMillis();
		System.out.println("---------------------"+ DateFormat.getTimeInstance().format(start)+ "-------------");		
		System.out.println("Время запуска: "+  start);		
		
		Object r = jp.proceed();
		
		long end = System.currentTimeMillis();		
		System.out.println("Время остановки:  "+ end);
		System.out.println("Выполнение метода "+jp.getSignature()+" заняло "+ (end-start)+ " мсек." );
        return r;    		
	}
	
	//@AfterThrowing("execution(* springhw.aopendtoendservices.NumGenerator.getSqrtSum(..))")
    //public void calcStertInfo() {
    	//System.out.println("Аспект: Под знаком корня отрицательное значение");
    //}
	
}
