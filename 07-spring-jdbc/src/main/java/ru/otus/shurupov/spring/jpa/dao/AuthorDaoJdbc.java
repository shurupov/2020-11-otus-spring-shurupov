package ru.otus.shurupov.spring.jpa.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.shurupov.spring.jpa.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from author", Integer.class);
    }

    @Override
    public Author getById(Long id) {
        return jdbc.queryForObject(
                "select id, first_name, last_name from author where id = :id",
                Map.of("id", id),
                new AuthorMapper()
        );
    }

    @Override
    public void insert(Author author) {
        jdbc.update(
                "insert into author (first_name, last_name) values (:first, :last)",
                Map.of(
                        "first", author.getFirstName(),
                        "last", author.getLastName()
                )
        );
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query(
                "select id, first_name, last_name from author",
                new AuthorMapper()
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")
            );
        }
    }
}
