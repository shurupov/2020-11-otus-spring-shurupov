package ru.otus.shurupov.spring.reactive.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.shurupov.spring.reactive.domain.dto.BookDto;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "book")
public class Book {
    @Id
    private String id;

    private Author author;
    private String name;
    private List<String> genres;
    private List<String> comments;

    public Book(Author author, String name, List<String> genres, List<String> comments) {
        this.author = author;
        this.name = name;
        this.genres = genres;
        this.comments = comments;
    }
}
