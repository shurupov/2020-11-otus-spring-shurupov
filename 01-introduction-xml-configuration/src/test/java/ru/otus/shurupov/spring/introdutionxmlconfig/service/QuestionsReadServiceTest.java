package ru.otus.shurupov.spring.introdutionxmlconfig.service;

import org.junit.jupiter.api.Test;
import ru.otus.shurupov.spring.introdutionxmlconfig.domain.Question;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionsReadServiceTest {

    private QuestionsReadService questionsReadService;

    @Test
    public void readQuestions() throws IOException {
        questionsReadService = new QuestionsReadService("testquestions.csv");
        List<Question> questions = questionsReadService.readQuestions();
        assertEquals(2, questions.size());
        assertEquals("The first question", questions.get(0).getQuestion());
        assertEquals(3, questions.get(0).getAnswers().size());
        assertEquals("The first answer", questions.get(0).getAnswers().get(0));
        assertEquals("The second answer", questions.get(0).getAnswers().get(1));
        assertEquals("The third answer", questions.get(0).getAnswers().get(2));
        assertEquals("The second question", questions.get(1).getQuestion());
        assertEquals(3, questions.get(1).getAnswers().size());
        assertEquals("1", questions.get(1).getAnswers().get(0));
        assertEquals("2", questions.get(1).getAnswers().get(1));
        assertEquals("3", questions.get(1).getAnswers().get(2));
    }
}