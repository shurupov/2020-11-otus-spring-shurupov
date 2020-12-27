package ru.otus.shurupov.spring.jpa.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.shurupov.spring.jpa.domain.Genre;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("GenreDaoJpa Repo ")
@DataJpaTest
@Import(GenreDaoJpa.class)
class GenreDaoJpaTest {

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("returns correct genres count in library")
    void shouldCount() {
        assertThat(genreDao.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("returns genre by id")
    void shouldGetById() {
        assertThat(genreDao.getById(2L).get()).isEqualTo(new Genre(2L, "Fairy Tale"));
    }

    @Test
    @DisplayName("inserts genre to database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldInsert() {
        genreDao.insert(new Genre("Horror"));
        assertAll(
                () -> assertThat(genreDao.count()).isEqualTo(4),
                () -> assertThat(em.find(Genre.class, 4L)).isEqualTo(new Genre(4L, "Horror"))
        );
    }

    @Test
    @DisplayName("returns list of all available genres")
    void shouldGetAll() {
        assertThat(genreDao.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrderElementsOf(
                        Arrays.asList(
                                new Genre(1L, "Crime and Detective"),
                                new Genre(2L, "Fairy Tale"),
                                new Genre(3L, "Drama")
                        )
                );
    }
}