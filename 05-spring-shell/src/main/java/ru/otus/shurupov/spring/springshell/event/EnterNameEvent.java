package ru.otus.shurupov.spring.springshell.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnterNameEvent)) return false;
        EnterNameEvent that = (EnterNameEvent) o;
        if (this.source != that.source) return false;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }
}
