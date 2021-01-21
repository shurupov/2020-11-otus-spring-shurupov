package ru.otus.shurupov.spring.springdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.shurupov.spring.springdatamongodb.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByNameContainingIgnoreCase(String name);
}
