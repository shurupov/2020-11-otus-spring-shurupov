package ru.otus.shurupov.spring.springshell.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.shurupov.spring.springshell.config.QuizProps;
import ru.otus.shurupov.spring.springshell.dao.QuestionDao;
import ru.otus.shurupov.spring.springshell.domain.Question;
import ru.otus.shurupov.spring.springshell.utils.QuizState;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("QuizServiceImpl")
class QuizServiceImplTest {
    private QuizServiceImpl quizService;
    private QuestionDao questionDao;
    private OutputService outputService;

    private final Question question1 = new Question("What?", List.of("Yes", "No", "Maybe"), 0);

    @BeforeEach
    private void setup() throws IOException {
        questionDao = mock(QuestionDao.class);
        when(questionDao.readQuestions()).thenReturn(List.of(question1));
        outputService = mock(OutputService.class);
        QuizProps props = new QuizProps();
        QuizProps.Questions questionsProps = new QuizProps.Questions();
        questionsProps.setCount(3);
        props.setQuestions(questionsProps);
        props.setLocale(Locale.ENGLISH);
        props.setScores("1,0.8,0.6");

        quizService = spy(new QuizServiceImpl(questionDao, outputService, props));
    }

    @Test
    @DisplayName("Entered name gradually")
    public void shouldEnterFirstNameAndLastName() {
        quizService.setFirstName("Bob");
        quizService.setFirstName("Evgeny");
        quizService.setLastName("Shurupov");
        assertAll(
                () -> verify(outputService, times(2)).println(eq("Enter also last name")),
                () -> verify(quizService, times(1)).startQuiz()
        );
    }

    @Test
    @DisplayName("Entered name gradually in another order")
    public void shouldEnterLastNameAndFirstName() {
        quizService.setLastName("Smith");
        quizService.setLastName("Shurupov");
        quizService.setFirstName("Evgeny");
        assertAll(
                () -> verify(outputService, times(2)).println(eq("Enter also first name")),
                () -> verify(quizService, times(1)).startQuiz()
        );
    }

    @Test
    @DisplayName("Entered full name")
    public void shouldEnterFullName() {
        quizService.setFullName("Evgeny", "Shurupov");
        verify(quizService, times(1)).startQuiz();
    }

    @Test
    @DisplayName("Started quiz")
    public void shouldCompleteEnteringName() throws IOException {
        quizService.startQuiz();
        assertAll(
                () -> verify(outputService, times(1)).println(eq("Hello"), eq(null), eq(null)),
                () -> verify(questionDao, times(1)).readQuestions(),
                () -> verify(quizService, times(1)).askQuestion(),
                () -> assertEquals(QuizState.QUESTIONS, quizService.getState()),
                () -> verify(quizService, times(1)).startQuiz()
        );
    }

    @Test
    @DisplayName("Asked question")
    public void shouldAskQuestion() {
        quizService.startQuiz();
        verify(quizService, times(1)).printQuestion(eq(question1));
    }

    @Test
    @DisplayName("Answered question and completed")
    public void shouldAnswerAndComplete() {
        quizService.startQuiz();
        quizService.answer(0);
        assertAll(
                () -> verify(outputService, times(1)).println(eq("Your answer is"), eq(question1.getAnswers().get(0))),
                () -> verify(outputService, times(1)).println(eq("your rating is"), eq(null), eq(null), eq(5)),
                () -> verify(quizService, times(1)).startIntroduction()
        );
    }

    @Test
    @DisplayName("Answered question and continued")
    public void shouldAnswerAndContinue() throws IOException {
        when(questionDao.readQuestions()).thenReturn(List.of(question1, question1));
        quizService.startQuiz();
        quizService.answer(0);
        verify(outputService, times(1)).println(eq("Your answer is"), eq(question1.getAnswers().get(0)));
        verify(outputService, times(0)).println(eq("your rating is"), eq(null), eq(null), any());
        verify(quizService, times(0)).startIntroduction();
        verify(quizService, times(2)).askQuestion();
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
        verify(outputService, times(1)).println(eq("Question"), eq("What?"), eq("1) Yes; 2) No; 3) Maybe"));
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
}