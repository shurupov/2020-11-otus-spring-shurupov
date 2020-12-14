package ru.otus.shurupov.spring.springshell.service;

import java.io.IOException;

public interface QuizService {

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void quiz() throws IOException;
}
