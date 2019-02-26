package com.project.aspects;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class LoggingAspect {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Before("execution(* com.project.repositories.*.*(..))")
	public void before(JoinPoint joinPoint) {
		logger.info("Called " + joinPoint.getSignature().getName());
		logger.info(joinPoint.toString());
	}

	@AfterThrowing(value="execution(* com.project.services.*.*(..))",throwing="ex")
	public void aspectAfterThrowing(JoinPoint joinPoint, Exception ex) {
		logger.info("Exception in " + joinPoint.getSignature().getName() +" with args " +  joinPoint.getArgs());
		logger.info(ex.getMessage() + ex.getStackTrace());
	}
}
