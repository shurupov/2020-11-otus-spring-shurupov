package ru.otus.shurupov.spring.springshell.service;

public interface OutputService {
    void printMessage(String message, Object ...parameters);
    void println(String text);
    void println();
}
