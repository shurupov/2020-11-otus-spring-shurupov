package ru.otus.shurupov.spring.springdatamongodb.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "book")
public class Book {

    @Id
    private String id;
    private String name;
}
