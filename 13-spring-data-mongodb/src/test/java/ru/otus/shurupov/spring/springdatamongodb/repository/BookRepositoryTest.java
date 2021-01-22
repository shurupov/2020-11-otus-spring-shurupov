package ru.otus.shurupov.spring.springdatamongodb.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.shurupov.spring.springdatamongodb.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("BookRepository ")
@DataMongoTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("filters by name")
    void shouldFindByNameContainingIgnoreCase() {
        List<Book> books = bookRepository.findByNameContainingIgnoreCase("holm");
        assertAll(
                () -> assertThat(books).hasSize(1),
                () -> assertThat(books.get(0).getName()).isEqualTo("Sherlock Holmes. A Study in Scarlet")
        );
    }
}