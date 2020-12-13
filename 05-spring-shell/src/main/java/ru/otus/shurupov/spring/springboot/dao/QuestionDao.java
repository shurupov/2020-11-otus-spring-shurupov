package ru.otus.shurupov.spring.springboot.dao;

import ru.otus.shurupov.spring.springboot.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {
    List<Question> readQuestions() throws IOException;
}
