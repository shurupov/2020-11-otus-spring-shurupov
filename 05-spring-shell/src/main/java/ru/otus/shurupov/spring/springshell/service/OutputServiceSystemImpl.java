package ru.otus.shurupov.spring.springshell.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.springshell.config.QuizProps;

import java.util.Locale;

@Service
public class OutputServiceSystemImpl implements OutputService {

    private final MessageSource messageSource;
    private final Locale locale;


    public OutputServiceSystemImpl(MessageSource messageSource, QuizProps quizProps) {
        this.messageSource = messageSource;
        this.locale = quizProps.getLocale();
    }

    @Override
    public void println(String message, Object ...parameters) {
        System.out.println(messageSource.getMessage(message, parameters, locale));
    }

    @Override
    public void println() {
        System.out.println();
    }
}
