package ru.otus.shurupov.spring.jdbc.domain;

import lombok.Data;

@Data
public class Book {
    private final Long id;
    private final Long authorId;
    private final Long genreId;
    private final String name;

    public Book(String name, Long authorId, Long genreId) {
        this.id = null;
        this.authorId = authorId;
        this.genreId = genreId;
        this.name = name;
    }

    public Book(Long id, Long authorId, Long genreId, String name) {
        this.id = id;
        this.authorId = authorId;
        this.genreId = genreId;
        this.name = name;
    }
}
