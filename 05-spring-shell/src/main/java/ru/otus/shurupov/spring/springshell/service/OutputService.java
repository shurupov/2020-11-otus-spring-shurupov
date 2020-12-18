package ru.otus.shurupov.spring.springshell.service;

public interface OutputService {
    void println(String message, Object ...parameters);
    void println();
}
