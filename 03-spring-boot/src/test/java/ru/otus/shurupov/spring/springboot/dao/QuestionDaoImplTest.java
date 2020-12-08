package ru.otus.shurupov.spring.springboot.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.shurupov.spring.springboot.domain.Question;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("QuestionDaoImpl")
class QuestionDaoImplTest {

    private QuestionDaoImpl questionDao;

    @BeforeEach
    private void setup() {
        questionDao = new QuestionDaoImpl("testquestions.csv");
    }

    @DisplayName("Read questions correctly")
    @Test
    public void shouldReadQuestionsCorrectly() throws IOException {
        List<Question> questions = questionDao.readQuestions();
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