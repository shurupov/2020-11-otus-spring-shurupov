package ru.otus.shurupov.spring.springshell.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.shurupov.spring.springshell.config.QuizProps;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("InteractiveServiceSystemImpl")
class OutputServiceSystemImplTest {
    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    private OutputService outputService;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        QuizProps props = new QuizProps();
        props.setLocale(Locale.ENGLISH);
        outputService = new OutputServiceSystemImpl(messageSource(), props);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Performed println correctly")
    public void shouldPerformPrintln() {
        outputService.println("Hello", "Evgeny", "Shurupov");
        assertThat(outContent.toString()).endsWith("Hello, Evgeny Shurupov!" + System.getProperty("line.separator"));
    }

    @Test
    @DisplayName("Performed empty println correctly")
    public void shouldPerformEmptyPrintln() {
        outputService.println();
        assertThat(outContent.toString()).isEqualTo(System.getProperty("line.separator"));
    }

    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/quiz");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}