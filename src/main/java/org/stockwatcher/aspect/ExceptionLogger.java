package org.stockwatcher.aspect;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple aspect for logging exceptions being thrown.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class ExceptionLogger {
	private static final Logger LOGGER = 
		LoggerFactory.getLogger(ExceptionLogger.class);

	public void log(JoinPoint joinPoint, Exception exception) {
		LOGGER.error("Method {} in class {} threw {} with message: {}", 
			joinPoint.getSignature().getName(),
			joinPoint.getTarget().getClass().getName(),
			exception.getClass().getName(),
			exception.getMessage());
	}
}