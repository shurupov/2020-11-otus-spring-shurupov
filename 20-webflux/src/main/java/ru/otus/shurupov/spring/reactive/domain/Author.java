package ru.otus.shurupov.spring.reactive.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.shurupov.spring.reactive.domain.dto.AuthorDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "author")
public class Author {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    public Author(AuthorDto dto) {
        firstName = dto.getFirstName();
        lastName = dto.getLastName();
    }
}
