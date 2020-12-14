package ru.otus.shurupov.spring.springshell.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.springshell.service.QuizEventPublisher;
import ru.otus.shurupov.spring.springshell.service.QuizService;
import ru.otus.shurupov.spring.springshell.utils.QuizState;

@ShellComponent
@RequiredArgsConstructor
public class QuizShell {

    private final QuizEventPublisher quizEventPublisher;
    private final QuizService quizService;

    @ShellMethod(value = "Enter first name", key = {"f", "fn", "first-name"})
    @ShellMethodAvailability("isEnteringNameAvailable")
    public void enterFirstName(@ShellOption(defaultValue = "John") String firstName) {
        quizEventPublisher.publishFirstNameEntered(firstName);
    }

    @ShellMethod(value = "Enter last name", key = {"l", "ln", "last-name"})
    @ShellMethodAvailability("isEnteringNameAvailable")
    public void enterLastName(@ShellOption(defaultValue = "Doe") String lastName) {
        quizEventPublisher.publishLastNameEntered(lastName);
    }

    @ShellMethod(value = "Enter name", key = {"n", "name"})
    @ShellMethodAvailability("isEnteringNameAvailable")
    public void enterName(@ShellOption(defaultValue = "John") String firstName, @ShellOption(defaultValue = "Doe") String lastName) {
        quizEventPublisher.publishNameEntered(firstName, lastName);
    }

    @ShellMethod(value = "Enter answer number", key = {"a", "answer"})
    @ShellMethodAvailability("isAnsweringAvailable")
    public void enterAnswer(@ShellOption(defaultValue = "-1") int answerNumber) {
        quizEventPublisher.publishAnswered(answerNumber);
    }

    public Availability isEnteringNameAvailable() {
        return QuizState.ENTERING_NAME.equals(quizService.getState())
                ? Availability.available()
                : Availability.unavailable("you have already entered name");
    }

    public Availability isAnsweringAvailable() {
        return QuizState.QUESTIONS.equals(quizService.getState())
                ? Availability.available()
                : Availability.unavailable("first enter your name");
    }

}
