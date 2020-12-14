package ru.otus.shurupov.spring.springshell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.springshell.event.EnterFirstNameEvent;
import ru.otus.shurupov.spring.springshell.event.EnterLastNameEvent;
import ru.otus.shurupov.spring.springshell.event.EnterNameEvent;

@Service
@RequiredArgsConstructor
public class QuizEventListener {

    private final QuizService quizService;

    @EventListener
    public void setFirstName(EnterFirstNameEvent enterFirstNameEvent) {
        quizService.setFirstName(enterFirstNameEvent.getFirstName());
    }

    @EventListener
    public void setLastName(EnterLastNameEvent enterLastNameEvent) {
        quizService.setLastName(enterLastNameEvent.getLastName());
    }

    @EventListener
    public void setFullName(EnterNameEvent enterNameEvent) {
        quizService.setFullName(enterNameEvent.getFirstName(), enterNameEvent.getLastName());
    }
}
