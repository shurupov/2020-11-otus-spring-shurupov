package ru.otus.shurupov.spring.springdata.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.shurupov.spring.springdata.domain.Author;
import ru.otus.shurupov.spring.springdata.domain.Book;
import ru.otus.shurupov.spring.springdata.domain.Genre;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("BookRepository ")
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("updates one book in the table")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldUpdate() {
        bookRepository.updateNameById(3L, "Some another book");
        Book updated = em.find(Book.class, 3L);
        assertThat(updated.getName()).isEqualTo("Some another book");
    }

    @Test
    @DisplayName("filters by name")
    void shouldFilterByName() {
        List<Book> books = bookRepository.findByNameContainingIgnoreCase("pun");
        assertAll(
                () -> assertThat(books).hasSize(1),
                () -> assertThat(books.get(0).getName()).isEqualTo("Crime and Punishment")
        );
    }

    @Test
    @DisplayName("filters by author")
    void shouldFilterByAuthor() {
        List<Book> books = bookRepository.findByAuthorFirstNameContainingIgnoreCaseOrAuthorLastNameContainingIgnoreCase("lex", "lex");
        assertAll(
                () -> assertThat(books).hasSize(1),
                () -> assertThat(books.get(0).getAuthor().getFirstName()).isEqualTo("Alexander"),
                () -> assertThat(books.get(0).getName()).isEqualTo("The Tale about a Fisherman and a Fish")
        );
    }
}