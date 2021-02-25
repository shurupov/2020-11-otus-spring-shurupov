package ru.otus.shurupov.spring.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.shurupov.spring.reactive.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
