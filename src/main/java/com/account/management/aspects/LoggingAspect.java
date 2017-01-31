package com.account.management.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component("LoggingAspectBean")
public class LoggingAspect {
	//Get us a logger
	private Logger logger = Logger.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(public * com.account.management..*.*(..))")
	public void allMethods(){}
	

	@Before("allMethods()")
	public void logBeforeAdvice(JoinPoint joinPt){
		//try getting all arguments of method or inspect them using joinPt methods
		logger.info("Method called: " + joinPt.getSignature());
	}
	
		@AfterThrowing(pointcut="execution(public * com.account.management..*.*(..))", throwing = "ex")
	public void logAfterThrowingAdvice(JoinPoint joinPt, Exception ex){
		//try getting all arguments of method or inspect them using joinPt methods
		logger.info("Exception thrown is : " + ex);
		logger.info("Signature of exception: "+joinPt.getSignature());
	}
		/*
	
	@AfterThrowing("allMethods()")
	public void logExceptionMethods(JoinPoint joinPt){
		//try getting all arguments of method or inspect them using joinPt methods
		logger.info("Exception was thrown in " + joinPt.getSignature());
	}*/
	
	
	@Around("allMethods()")
	public Object logAfterAdvice(ProceedingJoinPoint joinPt) throws Throwable{
		//step 1: try getting all arguments of method or inspect them using joinPt methods
		Object result;
		logger.info("Around advice called: " + joinPt.getSignature());
		
		try{
			result=joinPt.proceed();
			logger.info("Return value:" + result);
			return result;
		}catch(IllegalArgumentException ex){
			logger.info("Method failed with exception:" + ex);
			throw ex;
		}
	}
	
	
	
}
