package ru.otus.shurupov.spring.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.springboot.event.EnterFirstNameEvent;

@Service
@RequiredArgsConstructor
public class QuizEventListenerImpl implements QuizEventListener {

    private final QuizService quizService;

    @Override
    @EventListener
    public void setFirstName(EnterFirstNameEvent enterFirstNameEvent) {
        quizService.setFirstName(enterFirstNameEvent.getFirstName());
    }
}
