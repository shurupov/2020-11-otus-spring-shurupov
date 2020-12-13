package ru.otus.shurupov.spring.springboot.service;

import ru.otus.shurupov.spring.springboot.event.EnterFirstNameEvent;

public interface QuizEventListener {
    void setFirstName(EnterFirstNameEvent enterFirstNameEvent);
}
