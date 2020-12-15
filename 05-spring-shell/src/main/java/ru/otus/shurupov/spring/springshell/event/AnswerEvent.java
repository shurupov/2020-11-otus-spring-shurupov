package ru.otus.shurupov.spring.springshell.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

public class AnswerEvent extends ApplicationEvent {

    @Getter
    private final int answerNumber;

    public AnswerEvent(Object source, int answerNumber) {
        super(source);
        this.answerNumber = answerNumber - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerEvent that = (AnswerEvent) o;
        if (this.source != that.source) return false;
        return answerNumber == that.answerNumber;
    }
}
