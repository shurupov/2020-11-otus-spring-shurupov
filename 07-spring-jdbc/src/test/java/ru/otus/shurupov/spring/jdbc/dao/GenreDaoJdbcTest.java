package ru.otus.shurupov.spring.jdbc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.jdbc.domain.Genre;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("GenreDaoJdbc")
@JdbcTest
@Import(GenreDaoJdbc.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("returns correct genres count in library")
    void shouldCount() {
        assertThat(genreDao.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("returns genre by id")
    void shouldGetById() {
        assertThat(genreDao.getById(2L)).isEqualTo(new Genre(2L, "Fairy Tale"));
    }

    @Test
    @DisplayName("inserts genre to database")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldInsert() {
        genreDao.insert(new Genre("Horror"));
        assertAll(
                () -> assertThat(genreDao.count()).isEqualTo(4),
                () -> assertThat(genreDao.getById(4L)).isEqualTo(new Genre(4L, "Horror"))
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