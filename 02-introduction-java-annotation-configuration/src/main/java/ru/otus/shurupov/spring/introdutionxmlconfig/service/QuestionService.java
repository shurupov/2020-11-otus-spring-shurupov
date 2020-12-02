package ru.otus.shurupov.spring.introdutionxmlconfig.service;

import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.introdutionxmlconfig.dao.QuestionsDao;
import ru.otus.shurupov.spring.introdutionxmlconfig.domain.Question;

import java.io.IOException;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionsDao questionsDao;

    public QuestionService(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    public void quiz() throws IOException {
        List<Question> questions = questionsDao.readQuestions();
        for (Question question : questions) {
            System.out.print(question);
        }
    }
}
