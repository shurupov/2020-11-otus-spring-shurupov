package ru.otus.shurupov.spring.springshell.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.shurupov.spring.springshell.config.QuizProps;
import ru.otus.shurupov.spring.springshell.dao.QuestionDao;
import ru.otus.shurupov.spring.springshell.domain.Question;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@DisplayName("QuizServiceImpl")
class QuizServiceImplTest {
    private QuizServiceImpl quizService;
    private QuestionDao questionDao;
    private InteractiveService interactiveService;

    @BeforeEach
    private void setup() {
        questionDao = mock(QuestionDao.class);
        interactiveService = mock(InteractiveService.class);
        QuizProps props = new QuizProps();
        QuizProps.Questions questionsProps = new QuizProps.Questions();
        questionsProps.setCount(3);
        props.setQuestions(questionsProps);
        props.setLocale(Locale.ENGLISH);
        props.setScores("1,0.8,0.6");

        quizService = new QuizServiceImpl(questionDao, interactiveService, messageSource(), props);
    }

    @Test
    @DisplayName("Printed question correctly")
    public void shouldPrintQuestion() {
        Question question = new Question(
                "What?",
                List.of("Yes", "No", "Maybe"),
                2
        );
        quizService.printQuestion(question);
        verify(interactiveService, times(1)).println(eq("Question: What?\nAnswers: 1) Yes; 2) No; 3) Maybe"));
    }

    @Test
    @DisplayName("Returned ratings correctly")
    public void shouldReturnRatingCorrectly() {
        assertAll(
                () -> assertThat(quizService.getRating(1)).isEqualTo(5),
                () -> assertThat(quizService.getRating(0.9f)).isEqualTo(4),
                () -> assertThat(quizService.getRating(0.8f)).isEqualTo(4),
                () -> assertThat(quizService.getRating(0.7f)).isEqualTo(3),
                () -> assertThat(quizService.getRating(0.6f)).isEqualTo(3),
                () -> assertThat(quizService.getRating(0.5f)).isEqualTo(2),
                () -> assertThat(quizService.getRating(0.4f)).isEqualTo(2),
                () -> assertThat(quizService.getRating(0.3f)).isEqualTo(2),
                () -> assertThat(quizService.getRating(0.2f)).isEqualTo(2),
                () -> assertThat(quizService.getRating(0.1f)).isEqualTo(2),
                () -> assertThat(quizService.getRating(0f)).isEqualTo(2)
        );
    }

    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/quiz");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}