package com.routinecart.component.order.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Pointcut("execution(* com.atuldwivedi.quickcart.component.order..*.*(..))")
	public void allMethods() {};
	
	@Before("allMethods()")
	public void methodEntry(JoinPoint joinpoint) {
		System.out.println(">> "+joinpoint.getSignature());
	}
	
	@AfterReturning("allMethods()")
	public void methodExit(JoinPoint joinpoint) {
		System.out.println("<< "+joinpoint.getSignature());
	}
}
