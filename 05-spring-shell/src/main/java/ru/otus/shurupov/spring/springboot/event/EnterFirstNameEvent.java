package ru.otus.shurupov.spring.springboot.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class EnterFirstNameEvent extends ApplicationEvent {

    @Getter
    private final String firstName;

    public EnterFirstNameEvent(Object source, String firstName) {
        super(source);
        this.firstName = firstName;
    }
}
