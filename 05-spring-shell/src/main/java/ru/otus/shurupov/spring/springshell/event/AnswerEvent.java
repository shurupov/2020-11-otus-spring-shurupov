package ru.otus.shurupov.spring.springshell.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class AnswerEvent extends ApplicationEvent {

    @Getter
    private final int answerNumber;

    public AnswerEvent(Object source, int answerNumber) {
        super(source);
        this.answerNumber = answerNumber - 1;
    }
}
