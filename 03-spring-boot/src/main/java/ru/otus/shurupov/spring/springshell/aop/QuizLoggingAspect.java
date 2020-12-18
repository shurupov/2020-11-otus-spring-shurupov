package ru.otus.shurupov.spring.springshell.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class QuizLoggingAspect {
    @Before("@annotation(ru.otus.shurupov.spring.springshell.aop.QuizLogging)")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Method started: " + joinPoint.getSignature().getName());
    }

    @After("@annotation(ru.otus.shurupov.spring.springshell.aop.QuizLogging)")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Method completed: " + joinPoint.getSignature().getName());
    }
}
