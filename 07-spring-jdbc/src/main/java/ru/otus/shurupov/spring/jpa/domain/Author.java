package ru.otus.shurupov.spring.jpa.domain;

import lombok.Data;

@Data
public class Author {
    private final Long id;
    private final String firstName;
    private final String lastName;

    public Author(String firstName, String lastName) {
        this.lastName = lastName;
        this.id = null;
        this.firstName = firstName;
    }

    public Author(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
