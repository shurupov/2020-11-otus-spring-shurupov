package ru.otus.shurupov.spring.springshell.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;
import ru.otus.shurupov.spring.springshell.event.AnswerEvent;
import ru.otus.shurupov.spring.springshell.event.EnterFirstNameEvent;
import ru.otus.shurupov.spring.springshell.event.EnterLastNameEvent;
import ru.otus.shurupov.spring.springshell.event.EnterNameEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("QuizEventPublisherImpl")
class QuizEventPublisherImplTest {

    private QuizEventPublisherImpl quizEventPublisher;
    private ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    void setUp() {
        applicationEventPublisher = mock(ApplicationEventPublisher.class);
        quizEventPublisher = new QuizEventPublisherImpl(applicationEventPublisher);
    }

    @Test
    @DisplayName("Published first name entered")
    void shouldPublishFirstNameEntered() {
        quizEventPublisher.publishFirstNameEntered("Evgeny");
        verify(applicationEventPublisher, times(1)).publishEvent(
                eq(new EnterFirstNameEvent(quizEventPublisher, "Evgeny"))
        );
    }

    @Test
    @DisplayName("Published last name entered")
    void publishLastNameEntered() {
        quizEventPublisher.publishLastNameEntered("Shurupov");
        verify(applicationEventPublisher, times(1)).publishEvent(
                eq(new EnterLastNameEvent(quizEventPublisher, "Shurupov"))
        );
    }

    @Test
    @DisplayName("Published full name entered")
    void publishNameEntered() {
        quizEventPublisher.publishNameEntered("Evgeny", "Shurupov");
        verify(applicationEventPublisher, times(1)).publishEvent(
                eq(new EnterNameEvent(quizEventPublisher, "Evgeny","Shurupov"))
        );
    }

    @Test
    @DisplayName("Published answered")
    void publishAnswered() {
        quizEventPublisher.publishAnswered(1);
        verify(applicationEventPublisher, times(1)).publishEvent(
                eq(new AnswerEvent(quizEventPublisher, 1))
        );
    }
}