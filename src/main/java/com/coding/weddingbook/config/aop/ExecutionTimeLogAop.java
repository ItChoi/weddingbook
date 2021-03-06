package com.coding.weddingbook.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExecutionTimeLogAop {

    @Around("execution(* com.coding.weddingbook.web.board.*Controller.*(..))")
    public Object executionTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("실행 시간: {}초", (end - start) / 1000.0);

        return proceed;
    }

}
