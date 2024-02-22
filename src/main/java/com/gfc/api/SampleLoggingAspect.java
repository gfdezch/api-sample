package com.gfc.api;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * This class contains the logging aspect.
 * The @Aspect annotation marks this class as an aspect.
 * The @Component annotation marks this class as a Spring component.
 * The @Around annotation marks the loggingAround method as an around advice.
 * The @AfterThrowing annotation marks the logAfterThrowingAllMethods method as an after throwing advice.
 * @see https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop
 */
@Aspect
@Component
public class SampleLoggingAspect {
    
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Logs all methods in the com.gfc.api package.
	 * @param joinPoint 
	 * @param result
	 * @throws Throwable
	 * @return the result of the method
	 * @see https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-pointcuts
	 */ 
    @Around("execution(public * com.gfc.api.*.*.*(..))")
	public Object loggingAround(ProceedingJoinPoint repositoryMethod) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		String logTrace = createJoinPointLogTrace(repositoryMethod);
		
		try {
            logger.info(logTrace);
			return repositoryMethod.proceed();
		} finally {		
			stopWatch.stop();
			
            logger.info(logTrace + " took " + stopWatch.getTotalTimeMillis() + " ms");			
		}
	}

	/** 
	 * Logs all exceptions thrown by any methods in the com.gfc.api package.
	 * @param ex
	 * @throws Throwable
	 */
	@AfterThrowing(pointcut = "execution(public * com.gfc.api.*.*.*(..))", throwing = "ex")
	public void logAfterThrowingAllMethods(Exception ex) throws Throwable {
		logger.error("Exception thrown", ex);
	}

	/**
	 * Creates a log trace for the given join point.
	 * @param joinPoint
	 * @return the log trace
	 */	
	private String createJoinPointLogTrace(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		String[] parameterNames = methodSignature.getParameterNames();
		Class<?>[] parameterTypes = methodSignature.getParameterTypes();

		StringBuilder sb = new StringBuilder();
		sb.append(signature.getDeclaringType().getSimpleName());
		sb.append('.').append(signature.getName());

		if (0 < joinPoint.getArgs().length ) {
			sb.append('(');
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				sb.append(parameterTypes[i].getSimpleName())
				.append(' ')
				.append(parameterNames[i])
				.append(": ")
				.append(joinPoint.getArgs()[i])
				.append(", ");
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(')');
		} else {
			sb.append("()");
		}

		return sb.toString();
	}
}
