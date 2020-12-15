package ru.otus.shurupov.spring.springshell.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@DisplayName("GreetingServiceImpl")
class GreetingServiceImplTest {

    private OutputService outputService;
    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
        outputService = mock(OutputService.class);
        greetingService = new GreetingServiceImpl(outputService);
    }

    @Test
    @DisplayName("Said hello")
    void sayHello() {
        greetingService.sayHello();
        verify(outputService, times(1)).println("Hello! This is a quiz application!");
    }
}