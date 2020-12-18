package ru.otus.shurupov.spring.springshell.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

public class EnterFirstNameEvent extends ApplicationEvent {

    @Getter
    private final String firstName;

    public EnterFirstNameEvent(Object source, String firstName) {
        super(source);
        this.firstName = firstName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.source, this.firstName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnterFirstNameEvent that = (EnterFirstNameEvent) o;
        if (this.source != that.source) return false;
        return Objects.equals(firstName, that.firstName);
    }
}
