package ru.otus.shurupov.spring.springshell.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("InteractiveServiceSystemImpl")
class OutputServiceSystemImplTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private OutputService outputService;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        //outputService = new OutputServiceSystemImpl(messageSource);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Performed println correctly")
    public void shouldPerformPrintln() {
        outputService.printMessage("hello");
        assertThat(outContent.toString()).isEqualTo("hello\n");
    }

    @Test
    @DisplayName("Performed empty println correctly")
    public void shouldPerformEmptyPrintln() {
        outputService.println();
        assertThat(outContent.toString()).isEqualTo("\n");
    }
}