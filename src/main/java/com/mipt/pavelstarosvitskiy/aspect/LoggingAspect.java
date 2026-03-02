package com.mipt.pavelstarosvitskiy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Зафиксировать время выполнения методов, их аргументы и исключения при выполнении
 */
@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.mipt.pavelstarosvitskiy.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("AOP: Начинаем выполнение метода: {} с аргументами: {}", methodName, Arrays.toString(args));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("AOP: Ошибка в методе {}: {}", methodName, e.getMessage());
            throw e;
        }

        long executionTime = System.currentTimeMillis() - start;

        log.info("AOP: Метод {} выполнен за {} мс с результатом: {}", methodName, executionTime, result);

        return result;
    }
}
