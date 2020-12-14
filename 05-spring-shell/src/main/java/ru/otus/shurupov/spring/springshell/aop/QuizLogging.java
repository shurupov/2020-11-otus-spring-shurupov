package ru.otus.shurupov.spring.springshell.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface QuizLogging {
}
