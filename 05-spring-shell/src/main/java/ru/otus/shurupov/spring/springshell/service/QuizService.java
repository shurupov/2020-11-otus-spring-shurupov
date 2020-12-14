package ru.otus.shurupov.spring.springshell.service;

import ru.otus.shurupov.spring.springshell.utils.QuizState;

import java.io.IOException;

public interface QuizService {

    QuizState getState();

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setFullName(String firstName, String lastName);

    void answer(int answerNumber);
}
