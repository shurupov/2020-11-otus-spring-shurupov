package ru.otus.shurupov.spring.introdutionxmlconfig.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.shurupov.spring.introdutionxmlconfig.domain.Question;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("QuestionDao")
class QuestionsDaoTest {

    private QuestionsDao questionsDao;

    @DisplayName("Read questions correctly")
    @Test
    public void shouldReadQuestionsCorrectly() throws IOException {
        questionsDao = new QuestionsDao("testquestions.csv");
        List<Question> questions = questionsDao.readQuestions();
        assertAll(
                () -> assertAll(
                        () -> assertThat(questions).hasSize(2),
                        () -> assertThat(questions.get(0).getQuestion()).isEqualTo("The first question"),
                        () -> assertThat(questions.get(0).getAnswers())
                                .hasSize(3)
                                .contains("The first answer", atIndex(0))
                                .contains("The second answer", atIndex(1))
                                .contains("The third answer", atIndex(2)),
                        () -> assertThat(questions.get(0).getCorrectAnswerNumber()).isEqualTo(0)
                ),
                () -> assertAll(
                        () -> assertThat(questions.get(1).getQuestion()).isEqualTo("The second question"),
                        () -> assertThat(questions.get(1).getAnswers())
                                .hasSize(3)
                                .contains("1", atIndex(0))
                                .contains("2", atIndex(1))
                                .contains("3", atIndex(2)),
                        () -> assertThat(questions.get(1).getCorrectAnswerNumber()).isEqualTo(2)
                )

        );
    }
}