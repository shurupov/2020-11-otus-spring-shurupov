package ru.otus.shurupov.spring.springshell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.springshell.event.EnterFirstNameEvent;
import ru.otus.shurupov.spring.springshell.event.EnterLastNameEvent;
import ru.otus.shurupov.spring.springshell.event.EnterNameEvent;

@Service
@RequiredArgsConstructor
public class QuizEventPublisherImpl implements QuizEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishFirstNameEntered(String firstName) {
        applicationEventPublisher.publishEvent(
                new EnterFirstNameEvent(this, firstName)
        );
    }

    @Override
    public void publishLastNameEntered(String lastName) {
        applicationEventPublisher.publishEvent(
                new EnterLastNameEvent(this, lastName)
        );
    }

    @Override
    public void publishNameEntered(String firstName, String lastName) {
        applicationEventPublisher.publishEvent(
                new EnterNameEvent(this, firstName, lastName)
        );
    }

}
