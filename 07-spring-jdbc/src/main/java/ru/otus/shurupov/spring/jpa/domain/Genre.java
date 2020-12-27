package ru.otus.shurupov.spring.jpa.domain;

import lombok.Data;

@Data
public class Genre {
    private final Long id;
    private final String name;

    public Genre(String name) {
        this.id = null;
        this.name = name;
    }

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
