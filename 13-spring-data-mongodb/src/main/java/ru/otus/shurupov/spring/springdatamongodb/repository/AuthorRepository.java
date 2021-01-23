package ru.otus.shurupov.spring.springdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.shurupov.spring.springdatamongodb.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
