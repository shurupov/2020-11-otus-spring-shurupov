package ru.otus.shurupov.spring.springshell.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("InteractiveServiceSystemImpl")
class InteractiveServiceSystemImplTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private InteractiveService interactiveService;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        interactiveService = new InteractiveServiceSystemImpl();
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Performed println correctly")
    public void shouldPerformPrintln() {
        interactiveService.println("hello");
        assertThat(outContent.toString()).isEqualTo("hello\n");
    }

    @Test
    @DisplayName("Performed empty println correctly")
    public void shouldPerformEmptyPrintln() {
        interactiveService.println();
        assertThat(outContent.toString()).isEqualTo("\n");
    }
}