package ru.otus.shurupov.spring.jpa.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.shurupov.spring.jpa.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from genre", Integer.class);
    }

    @Override
    public Genre getById(Long id) {
        return jdbc.queryForObject(
                "select id, name from genre where id = :id",
                Map.of("id", id),
                new GenreMapper()
        );
    }

    @Override
    public void insert(Genre genre) {
        jdbc.update(
                "insert into genre (name) values (:name)",
                Map.of(
                        "name", genre.getName()
                )
        );
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query(
                "select id, name from genre",
                Collections.emptyMap(),
                new GenreMapper()
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Genre(resultSet.getLong("id"), resultSet.getString("name"));
        }
    }
}
