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
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    public BookDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from book", Integer.class);
    }

    @Override
    public Book getById(Long id) {
        return jdbc.queryForObject(
                "select id, name from book where id = :id",
                Map.of("id", id),
                new BookMapper()
        );
    }

    @Override
    public void insert(Book book) {
        jdbc.update(
                "insert into book (id, `name`) values (:id, :name)",
                Map.of(
                        "id", book.getId(),
                        "name", book.getName()
                )
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select id, name from book",
                Collections.emptyMap(),
                new BookMapper()
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(resultSet.getLong("id"), resultSet.getString("name"));
        }
    }
}
