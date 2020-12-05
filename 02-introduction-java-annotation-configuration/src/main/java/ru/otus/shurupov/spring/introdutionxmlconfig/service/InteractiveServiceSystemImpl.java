package ru.otus.shurupov.spring.introdutionxmlconfig.service;

import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class InteractiveServiceSystemImpl implements InteractiveService {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readString() {
        return scanner.next();
    }

    @Override
    public int readInt() {
        return scanner.nextInt() - 1;
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void println() {
        System.out.println();
    }
}
