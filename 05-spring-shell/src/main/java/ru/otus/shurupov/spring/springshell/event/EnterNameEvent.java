package ru.otus.shurupov.spring.springshell.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class EnterNameEvent extends ApplicationEvent {

    @Getter
    private final String firstName;
    @Getter
    private final String lastName;

    public EnterNameEvent(Object source, String firstName, String lastName) {
        super(source);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
