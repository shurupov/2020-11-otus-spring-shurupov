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
import java.util.List;

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
        Book book = bookDao.getById(3L).get();
        assertAll(
                () -> assertThat(book.getId()).isEqualTo(3L),
                () -> assertThat(book.getName()).isEqualTo("The Tale about a Fisherman and a Fish"),
                () -> assertThat(book.getAuthor()).isEqualTo(new Author(3L, "Alexander", "Pushkin")),
                () -> assertThat(book.getGenres())
                        .hasSize(1)
                        .contains(new Genre(2L, "Fairy Tale"))
        );
    }

    @Test
    @DisplayName("inserts book to database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldInsert() {
        Author author = em.find(Author.class, 4L);
        Genre genre1 = em.find(Genre.class, 3L);
        Genre genre2 = em.find(Genre.class, 1L);
        Book book = new Book("Anna Karenina", author, Arrays.asList(genre1, genre2));
        bookDao.insert(book);
        Book actualBook = em.find(Book.class, 5L);
        assertAll(
                () -> assertThat(bookDao.count()).isEqualTo(5),
                () -> assertThat(actualBook.getId()).isEqualTo(5L),
                () -> assertThat(actualBook.getName()).isEqualTo("Anna Karenina"),
                () -> assertThat(actualBook.getAuthor()).isEqualTo(new Author(4L, "Fedor", "Dostoevsky")),
                () -> assertThat(actualBook.getGenres())
                    .hasSize(2)
                    .contains(new Genre(3L, "Drama"))
                    .contains(new Genre(1L, "Crime and Detective"))
        );
    }

    @Test
    @DisplayName("returns list of all books")
    void shouldGetAll() {
        List<Book> books = bookDao.getAll();
        assertAll(
                () -> assertThat(books).hasSize(4),
                () -> assertAll(
                        () -> assertThat(books.get(0).getId()).isEqualTo(1L),
                        () -> assertThat(books.get(0).getName()).isEqualTo("Sherlock Holmes. A Study in Scarlet"),
                        () -> assertThat(books.get(0).getAuthor()).isEqualTo(new Author(1L, "Arthur", "Conan Doyle")),
                        () -> assertThat(books.get(0).getGenres())
                                .hasSize(1)
                                .contains(new Genre(1L, "Crime and Detective"))
                ),
                () -> assertAll(
                        () -> assertThat(books.get(1).getId()).isEqualTo(2L),
                        () -> assertThat(books.get(1).getName()).isEqualTo("Hercule Poirot. The Mysterious Affair at Styles"),
                        () -> assertThat(books.get(1).getAuthor()).isEqualTo(new Author(2L, "Agatha", "Christie")),
                        () -> assertThat(books.get(1).getGenres())
                                .hasSize(2)
                                .contains(new Genre(1L, "Crime and Detective"))
                                .contains(new Genre(2L, "Fairy Tale"))
                ),
                () -> assertAll(
                        () -> assertThat(books.get(2).getId()).isEqualTo(3L),
                        () -> assertThat(books.get(2).getName()).isEqualTo("The Tale about a Fisherman and a Fish"),
                        () -> assertThat(books.get(2).getAuthor()).isEqualTo(new Author(3L, "Alexander", "Pushkin")),
                        () -> assertThat(books.get(2).getGenres())
                                .hasSize(1)
                                .contains(new Genre(2L, "Fairy Tale"))
                ),
                () -> assertAll(
                        () -> assertThat(books.get(3).getId()).isEqualTo(4L),
                        () -> assertThat(books.get(3).getName()).isEqualTo("Crime and Punishment"),
                        () -> assertThat(books.get(3).getAuthor()).isEqualTo(new Author(4L, "Fedor", "Dostoevsky")),
                        () -> assertThat(books.get(3).getGenres())
                                .hasSize(1)
                                .contains(new Genre(3L, "Drama"))
                )
        );
    }

    @Test
    @DisplayName("removes book from table")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldRemoveById() {
        bookDao.removeById(1L);
        List<Book> books = bookDao.getAll();
        assertAll(
                () -> assertThat(books).hasSize(3),
                () -> assertAll(
                        () -> assertThat(books.get(0).getId()).isEqualTo(2L),
                        () -> assertThat(books.get(0).getName()).isEqualTo("Hercule Poirot. The Mysterious Affair at Styles"),
                        () -> assertThat(books.get(0).getAuthor()).isEqualTo(new Author(2L, "Agatha", "Christie")),
                        () -> assertThat(books.get(0).getGenres())
                                .hasSize(2)
                                .contains(new Genre(1L, "Crime and Detective"))
                                .contains(new Genre(2L, "Fairy Tale"))
                ),
                () -> assertAll(
                        () -> assertThat(books.get(1).getId()).isEqualTo(3L),
                        () -> assertThat(books.get(1).getName()).isEqualTo("The Tale about a Fisherman and a Fish"),
                        () -> assertThat(books.get(1).getAuthor()).isEqualTo(new Author(3L, "Alexander", "Pushkin")),
                        () -> assertThat(books.get(1).getGenres())
                                .hasSize(1)
                                .contains(new Genre(2L, "Fairy Tale"))
                ),
                () -> assertAll(
                        () -> assertThat(books.get(2).getId()).isEqualTo(4L),
                        () -> assertThat(books.get(2).getName()).isEqualTo("Crime and Punishment"),
                        () -> assertThat(books.get(2).getAuthor()).isEqualTo(new Author(4L, "Fedor", "Dostoevsky")),
                        () -> assertThat(books.get(2).getGenres())
                                .hasSize(1)
                                .contains(new Genre(3L, "Drama"))
                )
        );
    }

    @Test
    @DisplayName("updates one book in table")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void update() {
        bookDao.updateNameById(3L, "Some another book");
        Book updated = em.find(Book.class, 3L);
        assertThat(updated.getName()).isEqualTo("Some another book");
    }
}