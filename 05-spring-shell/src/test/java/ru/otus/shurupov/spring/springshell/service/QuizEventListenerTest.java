package ru.otus.shurupov.spring.springshell.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.shurupov.spring.springshell.event.AnswerEvent;
import ru.otus.shurupov.spring.springshell.event.EnterFirstNameEvent;
import ru.otus.shurupov.spring.springshell.event.EnterLastNameEvent;
import ru.otus.shurupov.spring.springshell.event.EnterNameEvent;
import ru.otus.shurupov.spring.springshell.utils.QuizState;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuizEventListenerTest {

    private QuizEventListener quizEventListener;
    private QuizService quizService;

    @BeforeEach
    void setUp() {
        quizService = mock(QuizService.class);
        quizEventListener = new QuizEventListener(quizService);
    }

    @Test
    void setFirstName() {
        quizEventListener.setFirstName(
                new EnterFirstNameEvent(this, "Evgeny")
        );
        verify(quizService, times(1)).setFirstName(eq("Evgeny"));

    }

    @Test
    void setLastName() {
        quizEventListener.setLastName(
                new EnterLastNameEvent(this, "Shurupov")
        );
        verify(quizService, times(1)).setLastName(eq("Shurupov"));
    }

    @Test
    void setFullName() {
        quizEventListener.setFullName(
                new EnterNameEvent(this, "Evgeny", "Shurupov")
        );
        verify(quizService, times(1)).setFullName(eq("Evgeny"), eq("Shurupov"));
    }

    @Test
    void answer() {
        quizEventListener.answer(
                new AnswerEvent(this, 2)
        );
        verify(quizService, times(1)).answer(eq(1));
    }
}