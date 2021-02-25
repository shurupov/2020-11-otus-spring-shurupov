package ru.otus.shurupov.spring.reactive.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.shurupov.spring.reactive.domain.Book;

import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {
    private String authorId;
    private String name;
    private List<String> genres;
    private List<String> comments;

    public BookDto(Book book) {
        this.authorId = book.getAuthor().getId();
        this.name = book.getName();
        this.genres = book.getGenres();
        this.comments = book.getComments();
    }
}
