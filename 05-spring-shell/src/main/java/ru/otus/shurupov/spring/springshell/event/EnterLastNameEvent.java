package ru.otus.shurupov.spring.springshell.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

public class EnterLastNameEvent extends ApplicationEvent {

    @Getter
    private final String lastName;

    public EnterLastNameEvent(Object source, String lastName) {
        super(source);
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnterLastNameEvent)) return false;
        EnterLastNameEvent that = (EnterLastNameEvent) o;
        if (this.source != that.source) return false;
        return Objects.equals(lastName, that.lastName);
    }
}
