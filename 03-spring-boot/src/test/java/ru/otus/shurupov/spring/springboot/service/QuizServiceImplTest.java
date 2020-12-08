package ru.otus.shurupov.spring.springboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.shurupov.spring.springboot.dao.QuestionDao;
import ru.otus.shurupov.spring.springboot.domain.Question;

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
        quizService = new QuizServiceImpl(questionDao, interactiveService, messageSource(), 3, "1,0.8,0.6", Locale.ENGLISH);
    }

    @Test
    @DisplayName("Performed quiz correctly")
    public void shouldPerformQuiz() throws IOException {
        when(questionDao.readQuestions()).thenReturn(
                List.of(
                        new Question(
                                "What?",
                                List.of("Yes", "No", "Maybe"),
                                2
                        ),
                        new Question(
                                "Where?",
                                List.of("Here", "There", "Anywhere"),
                                2
                        ),
                        new Question(
                                "When?",
                                List.of("Tomorrow", "Yesterday", "Now"),
                                1
                        )
                )
        );
        when(interactiveService.readString()).thenReturn("Maxim", "Patashov");
        when(interactiveService.readInt()).thenReturn(2);
        quizService.quiz();
        assertAll(
                () -> verify(interactiveService, times(3)).readInt(),
                () -> verify(interactiveService, times(2)).readString(),
                () -> verify(interactiveService, times(1)).println(eq("Enter your first name")),
                () -> verify(interactiveService, times(1)).println(eq("Enter your last name")),
                () -> verify(interactiveService, times(1)).println(eq("Your answer is Maybe")),
                () -> verify(interactiveService, times(1)).println(eq("Your answer is Anywhere")),
                () -> verify(interactiveService, times(1)).println(eq("Your answer is Now")),
                () -> verify(interactiveService, times(1)).println(eq("Maxim Patashov, your rating is 3")),
                () -> verify(interactiveService, times(1)).println(eq("Question: What?\nAnswers: 1) Yes; 2) No; 3) Maybe")),
                () -> verify(interactiveService, times(1)).println(eq("Question: Where?\nAnswers: 1) Here; 2) There; 3) Anywhere")),
                () -> verify(interactiveService, times(1)).println(eq("Question: When?\nAnswers: 1) Tomorrow; 2) Yesterday; 3) Now"))
        );

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