package ru.otus.shurupov.spring.jdbc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.jdbc.domain.Book;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("BookDaoJdbc")
@JdbcTest
@Import(BookDaoJdbc.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookDaoJdbcTest {

    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("returns correct books count in library")
    void shouldCount() {
        assertThat(bookDao.count()).isEqualTo(4);
    }

    @Test
    @DisplayName("returns correct book by id")
    void shouldGetById() {
        assertThat(bookDao.getById(3L)).isEqualTo(new Book(3L, 3L, 2L, "The Tale about a Fisherman and a Fish"));
    }

    @Test
    @DisplayName("inserts book to database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldInsert() {
        bookDao.insert(new Book("Anna Karenina", 4L, 3L));
        assertAll(
                () -> assertThat(bookDao.count()).isEqualTo(5),
                () -> assertThat(bookDao.getById(5L)).isEqualTo(new Book(5L, 4L, 3L, "Anna Karenina"))
        );
    }

    @Test
    @DisplayName("returns list of all books")
    void shouldGetAll() {
        assertThat(bookDao.getAll())
                .hasSize(4)
                .containsExactlyInAnyOrderElementsOf(
                        Arrays.asList(
                                new Book(1L, 1L, 1L, "Sherlock Holmes. A Study in Scarlet"),
                                new Book(2L, 2L, 1L, "Hercule Poirot. The Mysterious Affair at Styles"),
                                new Book(3L, 3L, 2L, "The Tale about a Fisherman and a Fish"),
                                new Book(4L, 4L, 3L, "Crime and Punishment")
                        )
                );
    }

    @Test
    @DisplayName("removes book from table")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldRemoveById() {
        bookDao.removeById(1L);
        assertThat(bookDao.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrderElementsOf(
                        Arrays.asList(
                                new Book(2L, 2L, 1L, "Hercule Poirot. The Mysterious Affair at Styles"),
                                new Book(3L, 3L, 2L, "The Tale about a Fisherman and a Fish"),
                                new Book(4L, 4L, 3L, "Crime and Punishment")
                        )
                );
    }

    @Test
    @DisplayName("updates one book in table")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void update() {
        Book expected = new Book(3L, 1L, 1L, "Some another book");
        bookDao.update(expected);
        assertThat(bookDao.getById(3L)).isEqualTo(expected);
    }
}