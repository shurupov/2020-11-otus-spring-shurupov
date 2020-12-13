package ru.otus.shurupov.spring.springboot.service;

import java.io.IOException;

public interface QuizService {

    void setFirstName(String firstName);

    void quiz() throws IOException;
}
