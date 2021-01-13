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

@DisplayName("BookDao Repo ")
@DataJpaTest
@Import(BookRepository.class)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("updates one book in table")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void update() {
        bookRepository.updateNameById(3L, "Some another book");
        Book updated = em.find(Book.class, 3L);
        assertThat(updated.getName()).isEqualTo("Some another book");
    }
}