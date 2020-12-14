package ru.otus.shurupov.spring.springshell.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class EnterLastNameEvent extends ApplicationEvent {

    @Getter
    private final String lastName;

    public EnterLastNameEvent(Object source, String lastName) {
        super(source);
        this.lastName = lastName;
    }
}
