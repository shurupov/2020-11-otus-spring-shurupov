package ru.otus.shurupov.spring.reactive.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.shurupov.spring.reactive.domain.Author;
import ru.otus.shurupov.spring.reactive.domain.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldSetIdOnSave() {
        Mono<Book> bookMono = bookRepository.save(book());

        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertNotNull(book.getId()))
                .expectComplete()
                .verify();
    }

    private Book book() {
        return new Book(
                new Author("aabbcc", "Sergey", "Yesenin"),
                "Lyrics",
                List.of("Dramra", "Poem"),
                List.of("Cool")
        );
    }
}