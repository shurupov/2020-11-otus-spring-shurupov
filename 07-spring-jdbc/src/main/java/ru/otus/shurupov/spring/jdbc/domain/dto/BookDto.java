package ru.otus.shurupov.spring.jdbc.domain.dto;

import lombok.Data;
import ru.otus.shurupov.spring.jdbc.domain.Book;

@Data
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private String genre;

    public BookDto(Book book, String author, String genre) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = author;
        this.genre = genre;
    }
}
