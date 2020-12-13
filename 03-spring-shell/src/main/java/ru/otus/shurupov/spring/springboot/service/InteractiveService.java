package ru.otus.shurupov.spring.springboot.service;

public interface InteractiveService {
    String readString();
    int readInt();
    void println(String text);
    void println();
}
