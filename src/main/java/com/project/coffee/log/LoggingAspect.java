package com.project.coffee.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * Logs a message before a method in the service package starts execution.
     *
     * @param joinPoint The point in the code where the method is being called.
     */
    @Before("execution(* com.project.coffee.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Starting execution: {} with parameters: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    /**
     * Logs a message after a method in the service package completes execution.
     * This could be after a normal return or after an exception is thrown.
     *
     * @param joinPoint The point in the code where the method is being called.
     */
    @After("execution(* com.project.coffee.service.*.*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        logger.info("Completed execution: {}", joinPoint.getSignature().toShortString());
    }

    /**
     * Logs a message after a method in the service package has returned successfully.
     *
     * @param joinPoint The point in the code where the method is being called.
     * @param result The result returned by the method.
     */
    @AfterReturning(pointcut = "execution(* com.project.coffee.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method: {} returned: {}", joinPoint.getSignature().toShortString(), result);
    }

    /**
     * Logs a message after a method in the service package throws an exception.
     *
     * @param joinPoint The point in the code where the method is being called.
     * @param exception The exception thrown by the method.
     */
    @AfterThrowing(pointcut = "execution(* com.project.coffee.service.*.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Caught Exception in method: {} - Message: {}", joinPoint.getSignature().toShortString(), exception.getMessage());
    }
}
