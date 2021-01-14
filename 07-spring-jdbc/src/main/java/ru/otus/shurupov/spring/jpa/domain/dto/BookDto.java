package ru.otus.shurupov.spring.jpa.domain.dto;

import lombok.Data;
import ru.otus.shurupov.spring.jpa.domain.Author;
import ru.otus.shurupov.spring.jpa.domain.Book;
import ru.otus.shurupov.spring.jpa.domain.Genre;

@Data
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private String genre;

    public BookDto(Book book, Author author, Genre genre) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = String.format("%s %s (%s)", author.getFirstName(), author.getLastName(), author.getId());
        this.genre = String.format("%s (%s)", genre.getName(), genre.getId());
    }
}
