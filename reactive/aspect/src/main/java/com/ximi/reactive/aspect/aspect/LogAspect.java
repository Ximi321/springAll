package com.ximi.reactive.aspect.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        try {
            log.info("around");
            return pjp.proceed();
        } catch (Throwable t) {
            log.error("around ",t);
            throw t;
        }
    }

    @Before("pointcut()")
    public void before(){
        log.info("before pointcut");
    }

    @After("pointcut()")
    public void after(){
        log.info("after pointcut");
    }

    @AfterReturning(value = "pointcut()",returning = "returnObject")
    public void afterReturning(JoinPoint joinPoint, Object returnObject){
        log.info("afterReturning pointcut");
    }

    @AfterReturning("pointcut()")
    public void afterThrowing(){
        log.info("afterThrowing pointcut");
    }

    @Pointcut("execution(* com.ximi.reactive.aspect.service..*(..))")
    public void pointcut() {

    }
}
