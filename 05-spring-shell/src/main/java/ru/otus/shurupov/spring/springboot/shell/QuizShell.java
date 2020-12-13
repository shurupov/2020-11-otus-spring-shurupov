package ru.otus.shurupov.spring.springboot.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.springboot.service.QuizEventPublisher;

@ShellComponent
@RequiredArgsConstructor
public class QuizShell {

    private final QuizEventPublisher quizEventPublisher;

    @ShellMethod(value = "Enter first name", key = {"f", "fn", "first-name"})
    public void enterFirstName(@ShellOption(defaultValue = "John") String firstName) {
        quizEventPublisher.publishFirstNameEntered(firstName);
    }

}
