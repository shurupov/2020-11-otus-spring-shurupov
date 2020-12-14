package ru.otus.shurupov.spring.springshell.dao;

import ru.otus.shurupov.spring.springshell.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {
    List<Question> readQuestions() throws IOException;
}
