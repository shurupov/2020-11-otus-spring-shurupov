package ru.otus.shurupov.spring.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.shurupov.spring.reactive.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
