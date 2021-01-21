package ru.otus.shurupov.spring.springdatamongodb.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "author")
public class Author {
    @Id
    private String id;
    private String firstName;
    private String lastName;
}
