package ru.otus.shurupov.spring.jpa.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.shurupov.spring.jpa.domain.Author;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("AuthorDaoJpa Repo ")
@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("returns correct authors count in library")
    void shouldCount() {
        assertThat(authorDao.count()).isEqualTo(4);
    }

    @Test
    @DisplayName("returns correct author by id")
    void shouldGetById() {
        assertThat(authorDao.getById(3L).get()).isEqualTo(new Author(3L, "Alexander", "Pushkin"));
    }

    @Test
    @DisplayName("inserts author to database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldInsert() {
        authorDao.insert(new Author("Jane", "Austin"));
        assertAll(
                () -> assertThat(authorDao.count()).isEqualTo(5),
                () -> assertThat(authorDao.getById(5L).get()).isEqualTo(new Author(5L, "Jane", "Austin"))
        );
    }

    @Test
    @DisplayName("returns list of all available authors")
    void shouldGetAll() {
        assertThat(authorDao.getAll())
                .hasSize(4)
                .containsExactlyInAnyOrderElementsOf(
                        Arrays.asList(
                                new Author(1L, "Arthur", "Conan Doyle"),
                                new Author(2L, "Agatha", "Christie"),
                                new Author(3L, "Alexander", "Pushkin"),
                                new Author(4L, "Fedor", "Dostoevsky")
                        )
                );
    }
}