package ru.otus.shurupov.spring.springshell.service;

public interface InteractiveService {
    String readString();
    int readInt();
    void println(String text);
    void println();
}
