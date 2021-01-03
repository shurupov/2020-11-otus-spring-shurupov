package ru.otus.shurupov.spring.jpa.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.shurupov.spring.jpa.domain.Book;
import ru.otus.shurupov.spring.jpa.domain.BookComment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("BookCommentDaoJpa Repo ")
@DataJpaTest
@Import(BookCommentDaoJpa.class)
class BookCommentDaoJpaTest {

    @Autowired
    private BookCommentDao bookCommentDao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("returns correct comments count in library")
    void shouldCount() {
        assertThat(bookCommentDao.count()).isEqualTo(8);
    }

    @Test
    @DisplayName("returns correct author by id")
    void shouldGetById() {
        BookComment bookComment = bookCommentDao.getById(3L).get();
        assertAll(
                () -> assertThat(bookComment.getId()).isEqualTo(3L),
                () -> assertThat(bookComment.getText()).isEqualTo("I read this book very fast!"),
                () -> assertAll(
                        () -> assertThat(bookComment.getBook().getId()).isEqualTo(2L),
                        () -> assertThat(bookComment.getBook().getName()).isEqualTo("Hercule Poirot. The Mysterious Affair at Styles")
                )

        );
    }

    @Test
    @DisplayName("inserts comment to book")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldInsert() {
        Book book = em.find(Book.class, 3L);
        BookComment bookComment = new BookComment();
        bookComment.setBook(book);
        bookComment.setText("Test comment text");
        bookCommentDao.insert(bookComment);
        BookComment actualComment = em.find(BookComment.class, 9L);
        assertAll(
                () -> assertThat(bookCommentDao.count()).isEqualTo(9),
                () -> assertThat(actualComment.getId()).isEqualTo(9L),
                () -> assertThat(actualComment.getText()).isEqualTo("Test comment text"),
                () -> assertAll(
                        () -> assertThat(bookComment.getBook().getId()).isEqualTo(3L),
                        () -> assertThat(bookComment.getBook().getName()).isEqualTo("The Tale about a Fisherman and a Fish")
                )
        );
    }

    @Test
    @DisplayName("returns list of all comments")
    void shouldGetAll() {
        List<BookComment> comments = bookCommentDao.getAll();
        assertAll(
                () -> assertThat(comments).hasSize(8),
                () -> assertAll(
                        () -> assertThat(comments.get(0).getId()).isEqualTo(1L),
                        () -> assertThat(comments.get(0).getText()).isEqualTo("Amazing book"),
                        () -> assertThat(comments.get(0).getBook().getId()).isEqualTo(1L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(1).getId()).isEqualTo(2L),
                        () -> assertThat(comments.get(1).getText()).isEqualTo("It's cool"),
                        () -> assertThat(comments.get(1).getBook().getId()).isEqualTo(2L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(2).getId()).isEqualTo(3L),
                        () -> assertThat(comments.get(2).getText()).isEqualTo("I read this book very fast!"),
                        () -> assertThat(comments.get(2).getBook().getId()).isEqualTo(2L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(3).getId()).isEqualTo(4L),
                        () -> assertThat(comments.get(3).getText()).isEqualTo("It's a huge pleasure to read this book."),
                        () -> assertThat(comments.get(3).getBook().getId()).isEqualTo(3L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(4).getId()).isEqualTo(5L),
                        () -> assertThat(comments.get(4).getText()).isEqualTo("I've read it then gave to all my friends!"),
                        () -> assertThat(comments.get(4).getBook().getId()).isEqualTo(3L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(5).getId()).isEqualTo(6L),
                        () -> assertThat(comments.get(5).getText()).isEqualTo("It has something useful for real life."),
                        () -> assertThat(comments.get(5).getBook().getId()).isEqualTo(4L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(6).getId()).isEqualTo(7L),
                        () -> assertThat(comments.get(6).getText()).isEqualTo("Some girls should read this. It's about them."),
                        () -> assertThat(comments.get(6).getBook().getId()).isEqualTo(4L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(7).getId()).isEqualTo(8L),
                        () -> assertThat(comments.get(7).getText()).isEqualTo("Good. I saw the cartoon as well. Showd to my children."),
                        () -> assertThat(comments.get(7).getBook().getId()).isEqualTo(4L)
                )
        );
    }

    @Test
    @DisplayName("removes comment from table")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void removeById() {
        bookCommentDao.removeById(6L);
        List<BookComment> comments = bookCommentDao.getAll();
        assertAll(
                () -> assertThat(comments).hasSize(7),
                () -> assertAll(
                        () -> assertThat(comments.get(0).getId()).isEqualTo(1L),
                        () -> assertThat(comments.get(0).getText()).isEqualTo("Amazing book"),
                        () -> assertThat(comments.get(0).getBook().getId()).isEqualTo(1L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(1).getId()).isEqualTo(2L),
                        () -> assertThat(comments.get(1).getText()).isEqualTo("It's cool"),
                        () -> assertThat(comments.get(1).getBook().getId()).isEqualTo(2L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(2).getId()).isEqualTo(3L),
                        () -> assertThat(comments.get(2).getText()).isEqualTo("I read this book very fast!"),
                        () -> assertThat(comments.get(2).getBook().getId()).isEqualTo(2L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(3).getId()).isEqualTo(4L),
                        () -> assertThat(comments.get(3).getText()).isEqualTo("It's a huge pleasure to read this book."),
                        () -> assertThat(comments.get(3).getBook().getId()).isEqualTo(3L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(4).getId()).isEqualTo(5L),
                        () -> assertThat(comments.get(4).getText()).isEqualTo("I've read it then gave to all my friends!"),
                        () -> assertThat(comments.get(4).getBook().getId()).isEqualTo(3L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(5).getId()).isEqualTo(7L),
                        () -> assertThat(comments.get(5).getText()).isEqualTo("Some girls should read this. It's about them."),
                        () -> assertThat(comments.get(5).getBook().getId()).isEqualTo(4L)
                ),
                () -> assertAll(
                        () -> assertThat(comments.get(6).getId()).isEqualTo(8L),
                        () -> assertThat(comments.get(6).getText()).isEqualTo("Good. I saw the cartoon as well. Showd to my children."),
                        () -> assertThat(comments.get(6).getBook().getId()).isEqualTo(4L)
                )
        );
    }

    @Test
    void updateById() {
        bookCommentDao.updateById(6L, "New Comment text");
        BookComment commentUpdated = em.find(BookComment.class, 6L);
        assertThat(commentUpdated.getText()).isEqualTo("New Comment text");
    }
}