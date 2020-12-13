package ru.otus.shurupov.spring.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.springboot.event.EnterFirstNameEvent;

@Service
@RequiredArgsConstructor
public class QuizEventPublisherImpl implements QuizEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishFirstNameEntered(String firstName) {
        applicationEventPublisher.publishEvent(
                new EnterFirstNameEvent(this, firstName)
        );
    }

}
