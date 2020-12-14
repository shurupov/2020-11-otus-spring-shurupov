package ru.otus.shurupov.spring.springshell.service;

public interface QuizEventPublisher {
    void publishFirstNameEntered(String firstName);

    void publishLastNameEntered(String lastName);

    void publishNameEntered(String firstName, String lastName);
}
