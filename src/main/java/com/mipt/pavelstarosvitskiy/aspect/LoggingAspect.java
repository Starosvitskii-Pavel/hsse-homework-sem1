package com.mipt.pavelstarosvitskiy.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Зафиксировать время выполнения методов, их аргументы и исключения при выполнении
 */
@Aspect
@Component
public class LoggingAspect {
    @Around("execution(* com.mipt.pavelstarosvitskiy.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("AOP: Начинаем выполнение метода: " + methodName);
        System.out.println("AOP: Аргументы: " + Arrays.toString(args));

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("AOP: Ошибка в методе " + methodName + ": " + e.getMessage());
            throw e;
        }

        long executionTime = System.currentTimeMillis() - start;

        System.out.println("AOP: Метод " + methodName + " выполнен за " + executionTime + "мс");
        System.out.println("AOP: Результат: " + result);

        return result;
    }
}
