package ru.otus.shurupov.spring.springshell.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@ConditionalOnProperty(name = "quiz.greeting", havingValue = "true")
public class GreetingImpl implements Greeting {
    @Override
    @PostConstruct
    public void sayHello() {
        System.out.println("Hello! This is a quiz application!");
    }
}
