package ru.otus.shurupov.spring.springshell.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.shurupov.spring.springshell.dao.QuestionDao;
import ru.otus.shurupov.spring.springshell.shell.QuizShell;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("QuizService integration test")
@SpringBootTest
public class QuizServiceIntTest {
    /*
     * This mock is not used but declared here to exclude it from context
     * to avoid usage of configuration that doesn't exist
     */
    @MockBean
    private QuestionDao questionDao;
    @MockBean
    private QuizService quizService;
    @Autowired
    private QuizShell shell;

    @Test
    @DisplayName("Entered first name")
    public void shouldEnterFirstName() {
        shell.enterFirstName("Evgeny");
        verify(quizService, times(1)).setFirstName(eq("Evgeny"));
    }

    @Test
    @DisplayName("Entered last name")
    public void shouldEnterLastName() {
        shell.enterLastName("Shurupov");
        verify(quizService, times(1)).setLastName(eq("Shurupov"));
    }

    @Test
    @DisplayName("Entered full name")
    public void shouldEnterFullName() {
        shell.enterName("Evgeny", "Shurupov");
        verify(quizService, times(1)).setFullName(eq("Evgeny"), eq("Shurupov"));
    }

    @Test
    @DisplayName("Entered answer")
    public void shouldEnterAnswer() {
        shell.enterAnswer(1);
        verify(quizService, times(1)).answer(eq(0));
    }

    @Test
    @DisplayName("Entered answer 1")
    public void shouldEnterAnswer1() {
        shell.enterAnswer1();
        verify(quizService, times(1)).answer(eq(0));
    }

    @Test
    @DisplayName("Entered answer 2")
    public void shouldEnterAnswer2() {
        shell.enterAnswer2();
        verify(quizService, times(1)).answer(eq(1));
    }

    @Test
    @DisplayName("Entered answer 3")
    public void shouldEnterAnswer3() {
        shell.enterAnswer3();
        verify(quizService, times(1)).answer(eq(2));
    }
}
