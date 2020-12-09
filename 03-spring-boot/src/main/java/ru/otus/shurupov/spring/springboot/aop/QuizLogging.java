package ru.otus.shurupov.spring.springboot.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface QuizLogging {
}
