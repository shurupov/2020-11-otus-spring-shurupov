package ru.otus.shurupov.spring.springdatamongodb.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "book")
public class Book {

    @Id
    private String id;

    @DBRef
    private Author author;
    private String name;
    private List<String> genres;
}