package ru.otus.shurupov.spring.springshell.service;

import org.springframework.stereotype.Service;

@Service
public class OutputServiceSystemImpl implements OutputService {
    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void println() {
        System.out.println();
    }
}
