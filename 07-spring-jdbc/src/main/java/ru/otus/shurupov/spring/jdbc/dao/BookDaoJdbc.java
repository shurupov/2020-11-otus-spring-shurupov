package ru.otus.shurupov.spring.jdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.shurupov.spring.jdbc.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from book", Integer.class);
    }

    @Override
    public Book getById(Long id) {
        return jdbc.queryForObject(
                "select id, author_id, genre_id, name from book where id = :id",
                Map.of("id", id),
                new BookMapper()
        );
    }

    @Override
    public void insert(Book book) {
        jdbc.update(
                "insert into book (name, author_id, genre_id) values (:name, :author_id, :genre_id)",
                Map.of(
                        "name", book.getName(),
                        "author_id", book.getAuthorId(),
                        "genre_id", book.getGenreId()
                )
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select id, author_id, genre_id, name from book",
                Collections.emptyMap(),
                new BookMapper()
        );
    }

    @Override
    public void removeById(Long id) {
        jdbc.update("delete from book where id = :id", Map.of("id", id));
    }

    @Override
    public void update(Book book) {
        jdbc.update(
                "update book set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id",
                Map.of(
                        "id", book.getId(),
                        "author_id", book.getAuthorId(),
                        "genre_id", book.getGenreId(),
                        "name", book.getName()
                )
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(
                    resultSet.getLong("id"),
                    resultSet.getLong("author_id"),
                    resultSet.getLong("genre_id"),
                    resultSet.getString("name")
            );
        }
    }
}