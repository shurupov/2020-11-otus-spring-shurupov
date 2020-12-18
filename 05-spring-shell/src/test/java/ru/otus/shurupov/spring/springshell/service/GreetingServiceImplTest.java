package ru.otus.shurupov.spring.springshell.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@DisplayName("GreetingServiceImpl")
@ExtendWith(MockitoExtension.class)
class GreetingServiceImplTest {

    @Mock
    private OutputService outputService;
    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
        greetingService = new GreetingServiceImpl(outputService);
    }

    @Test
    @DisplayName("Said hello")
    void sayHello() {
        greetingService.sayHello();
        verify(outputService, times(1)).println("Hello! This is a quiz application!");
    }
}