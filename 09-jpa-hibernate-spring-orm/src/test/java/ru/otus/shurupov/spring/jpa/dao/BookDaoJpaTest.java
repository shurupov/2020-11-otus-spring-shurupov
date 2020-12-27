package ru.otus.shurupov.spring.jpa.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.shurupov.spring.jpa.domain.Author;
import ru.otus.shurupov.spring.jpa.domain.Book;
import ru.otus.shurupov.spring.jpa.domain.Genre;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("BookDaoJpa Repo ")
@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("returns correct books count in library")
    void shouldCount() {
        assertThat(bookDao.count()).isEqualTo(4);
    }

    @Test
    @DisplayName("returns correct book by id")
    void shouldGetById() {
        assertThat(bookDao.getById(3L).get()).isEqualTo(
                new Book(
                        3L,
                        new Author(3L, "Alexander", "Pushkin"),
                        new Genre(2L, "Fairy Tale"),
                        "The Tale about a Fisherman and a Fish"
                )
        );
    }

    @Test
    @DisplayName("inserts book to database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldInsert() {
        Author author = em.find(Author.class, 4L);
        Genre genre = em.find(Genre.class, 3L);
        bookDao.insert(new Book("Anna Karenina", author, genre));
        assertAll(
                () -> assertThat(bookDao.count()).isEqualTo(5),
                () -> assertThat(em.find(Book.class, 5L)).isEqualTo(
                        new Book(
                                5L,
                                new Author(4L, "Fedor", "Dostoevsky"),
                                new Genre(3L, "Drama"),
                                "Anna Karenina"
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
                                new Book(
                                        1L,
                                        new Author(1L, "Arthur", "Conan Doyle"),
                                        new Genre(1L, "Crime and Detective"),
                                        "Sherlock Holmes. A Study in Scarlet"
                                ),
                                new Book(
                                        2L,
                                        new Author(2L, "Agatha", "Christie"),
                                        new Genre(1L, "Crime and Detective"),
                                        "Hercule Poirot. The Mysterious Affair at Styles"
                                ),
                                new Book(
                                        3L,
                                        new Author(3L, "Alexander", "Pushkin"),
                                        new Genre(2L, "Fairy Tale"),
                                        "The Tale about a Fisherman and a Fish"
                                ),
                                new Book(
                                        4L,
                                        new Author(4L, "Fedor", "Dostoevsky"),
                                        new Genre(3L, "Drama"),
                                        "Crime and Punishment"
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
                                new Book(
                                        2L,
                                        new Author(2L, "Agatha", "Christie"),
                                        new Genre(1L, "Crime and Detective"),
                                        "Hercule Poirot. The Mysterious Affair at Styles"
                                ),
                                new Book(
                                        3L,
                                        new Author(3L, "Alexander", "Pushkin"),
                                        new Genre(2L, "Fairy Tale"),
                                        "The Tale about a Fisherman and a Fish"
                                ),
                                new Book(
                                        4L,
                                        new Author(4L, "Fedor", "Dostoevsky"),
                                        new Genre(3L, "Drama"),
                                        "Crime and Punishment"
                                )
                        )
                );
    }

    @Test
    @DisplayName("updates one book in table")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void update() {
        Book expected = new Book(
                3L,
                new Author(3L, "Alexander", "Pushkin"),
                new Genre(2L, "Fairy Tale"),
                "Some another book"
        );
        bookDao.updateNameById(3L, "Some another book");
        assertThat(em.find(Book.class, 3L)).isEqualTo(expected);
    }
}