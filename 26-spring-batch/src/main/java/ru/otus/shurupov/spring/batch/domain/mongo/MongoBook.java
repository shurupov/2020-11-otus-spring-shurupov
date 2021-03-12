package ru.otus.shurupov.spring.batch.domain.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "book")
public class MongoBook {
    @Id
    private String id;

    private MongoAuthor author;
    private String name;
    private List<String> genres;
    private List<String> comments;
}
