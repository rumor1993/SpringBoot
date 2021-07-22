package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceApp {

    @Around("execution(* com.example.demo..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        System.out.println("START : " + start);
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("Finish : " + timeMs);
        }
    }
}
