package ru.otus.shurupov.spring.springboot.service;

public interface QuizEventPublisher {
    void publishFirstNameEntered(String firstName);
}
