package ru.otus.shurupov.spring.springshell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@ConditionalOnProperty(name = "quiz.greeting", havingValue = "true")
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {

    private final OutputService outputService;

    @Override
    @PostConstruct
    public void sayHello() {
        outputService.println("Hello! This is a quiz application!");
    }
}
