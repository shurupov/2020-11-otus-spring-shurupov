package ru.otus.shurupov.spring.reactive.mongock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.shurupov.spring.reactive.domain.Book;

public interface BookSyncRepository extends MongoRepository<Book, String> {
}
