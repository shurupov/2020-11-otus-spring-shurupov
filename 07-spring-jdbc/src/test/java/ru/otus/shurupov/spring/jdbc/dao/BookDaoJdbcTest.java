package ru.otus.shurupov.spring.jdbc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.jdbc.domain.Author;
import ru.otus.shurupov.spring.jdbc.domain.Book;
import ru.otus.shurupov.spring.jdbc.domain.Genre;
import ru.otus.shurupov.spring.jdbc.domain.dto.BookDto;

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
        assertThat(bookDao.getById(3L)).isEqualTo(
                new BookDto(
                        new Book(3L, 3L, 2L, "The Tale about a Fisherman and a Fish"),
                        new Author(3L, "Alexander", "Pushkin"),
                        new Genre(2L, "Fairy Tale")
                )
        );
    }

    @Test
    @DisplayName("inserts book to database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldInsert() {
        bookDao.insert(new Book("Anna Karenina", 4L, 3L));
        assertAll(
                () -> assertThat(bookDao.count()).isEqualTo(5),
                () -> assertThat(bookDao.getById(5L)).isEqualTo(
                        new BookDto(
                                new Book(5L, 4L, 3L, "Anna Karenina"),
                                new Author(4L, "Fedor", "Dostoevsky"),
                                new Genre(3L, "Drama")
                        )
                )
        );
    }

    @Test
    @DisplayName("returns list of all books")
    void shouldGetAll() {
        assertThat(bookDao.getAll())
                .hasSize(4)
                .containsExactlyInAnyOrderElementsOf(
                        Arrays.asList(
                                new BookDto(
                                        new Book(1L, 1L, 1L, "Sherlock Holmes. A Study in Scarlet"),
                                        new Author(1L, "Arthur", "Conan Doyle"),
                                        new Genre(1L, "Crime and Detective")
                                ),
                                new BookDto(
                                        new Book(2L, 2L, 1L, "Hercule Poirot. The Mysterious Affair at Styles"),
                                        new Author(2L, "Agatha", "Christie"),
                                        new Genre(1L, "Crime and Detective")
                                ),
                                new BookDto(
                                        new Book(3L, 3L, 2L, "The Tale about a Fisherman and a Fish"),
                                        new Author(3L, "Alexander", "Pushkin"),
                                        new Genre(2L, "Fairy Tale")
                                ),
                                new BookDto(
                                        new Book(4L, 4L, 3L, "Crime and Punishment"),
                                        new Author(4L, "Fedor", "Dostoevsky"),
                                        new Genre(3L, "Drama")
                                )
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
                                new BookDto(
                                        new Book(2L, 2L, 1L, "Hercule Poirot. The Mysterious Affair at Styles"),
                                        new Author(2L, "Agatha", "Christie"),
                                        new Genre(1L, "Crime and Detective")
                                ),
                                new BookDto(
                                        new Book(3L, 3L, 2L, "The Tale about a Fisherman and a Fish"),
                                        new Author(3L, "Alexander", "Pushkin"),
                                        new Genre(2L, "Fairy Tale")
                                ),
                                new BookDto(
                                        new Book(4L, 4L, 3L, "Crime and Punishment"),
                                        new Author(4L, "Fedor", "Dostoevsky"),
                                        new Genre(3L, "Drama")
                                )
                        )
                );
    }

    @Test
    @DisplayName("updates one book in table")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void update() {
        BookDto expected = new BookDto(
                new Book(3L, 1L, 1L, "Some another book"),
                new Author(1L, "Arthur", "Conan Doyle"),
                new Genre(1L, "Crime and Detective")
        );
        bookDao.update(new Book(3L, 1L, 1L, "Some another book"));
        assertThat(bookDao.getById(3L)).isEqualTo(expected);
    }
}