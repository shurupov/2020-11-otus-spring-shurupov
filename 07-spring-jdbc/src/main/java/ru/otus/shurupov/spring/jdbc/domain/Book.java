package ru.otus.shurupov.spring.jdbc.domain;

import lombok.Data;

@Data
public class Book {
    private final Long id;
    private final String name;

    public Book(String name) {
        this.id = null;
        this.name = name;
    }

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
