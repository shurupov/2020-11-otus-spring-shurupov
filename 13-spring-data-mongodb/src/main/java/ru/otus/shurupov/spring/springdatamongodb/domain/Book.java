package ru.otus.shurupov.spring.springdatamongodb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "book")
public class Book {

    @Id
    private String id;

    @DBRef
    private Author author;
    private String name;
    private List<String> genres;

    public Book(Author author, String name, List<String> genres) {
        this.author = author;
        this.name = name;
        this.genres = genres;
    }
}
