package com.example.jedi.util;

import java.util.Arrays;
import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("within(@org.springframework.stereotype.Service *)")
	public void executeLogging() {
		//do nothing because @Around does the job
	}

	@Around(value = "executeLogging()")
	public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
		logBeforeProceed(joinPoint);
		Object returnValue = joinPoint.proceed();
		logAfterProceed(joinPoint, returnValue);
		return returnValue;
	}

	private void logBeforeProceed(ProceedingJoinPoint joinPoint) {
		var beforeMessage = new StringBuilder("Method: ");
		beforeMessage.append(joinPoint.getSignature().getDeclaringType());
		beforeMessage.append(".");
		beforeMessage.append(joinPoint.getSignature().getName());
		Object[] args = joinPoint.getArgs();
		if (null!=args && args.length>0) {
			beforeMessage.append(" args=[ | ");
			Arrays.asList(args).forEach(arg->
				beforeMessage.append(arg).append(" | ")
			);
			beforeMessage.append("]");
			
		}
		LOGGER.info("{}",beforeMessage);
	}

	private void logAfterProceed(ProceedingJoinPoint joinPoint, Object returnValue) {
		var afterMessage = new StringBuilder();
		afterMessage.append(joinPoint.getSignature().getDeclaringType());
		afterMessage.append(".");
		afterMessage.append(joinPoint.getSignature().getName());
		afterMessage.append(" method returning: ");
		if (returnValue instanceof Collection) {
			afterMessage.append(((Collection<?>) returnValue).size()).append(" instance(s)");
		} else {
			afterMessage.append(returnValue.toString());
		}
		LOGGER.info("{}",afterMessage);
	}
}