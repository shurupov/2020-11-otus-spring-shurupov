package ru.otus.shurupov.spring.springshell.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.springshell.service.QuizEventPublisher;

@ShellComponent
@RequiredArgsConstructor
public class QuizShell {

    private final QuizEventPublisher quizEventPublisher;

    @ShellMethod(value = "Enter first name", key = {"f", "fn", "first-name"})
    public void enterFirstName(@ShellOption(defaultValue = "John") String firstName) {
        quizEventPublisher.publishFirstNameEntered(firstName);
    }

    @ShellMethod(value = "Enter last name", key = {"l", "ln", "last-name"})
    public void enterLastName(@ShellOption(defaultValue = "Doe") String lastName) {
        quizEventPublisher.publishLastNameEntered(lastName);
    }

    @ShellMethod(value = "Enter name", key = {"n", "name"})
    public void enterName(@ShellOption(defaultValue = "John") String firstName, @ShellOption(defaultValue = "Doe") String lastName) {
        quizEventPublisher.publishNameEntered(firstName, lastName);
    }
}
