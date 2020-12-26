package ru.otus.shurupov.spring.jdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.shurupov.spring.jdbc.domain.Author;
import ru.otus.shurupov.spring.jdbc.domain.Book;
import ru.otus.shurupov.spring.jdbc.domain.Genre;
import ru.otus.shurupov.spring.jdbc.domain.dto.BookDto;

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
    public BookDto getById(Long id) {
        return jdbc.queryForObject(
                "select b.id, b.author_id, b.genre_id, b.name, g.name as genre_name, a.first_name, a.last_name\n" +
                    "from book b\n" +
                    "join genre g on g.id = b.genre_id\n" +
                    "join author a on a.id = b.author_id\n" +
                    "where b.id = :id",
                Map.of("id", id),
                new BookDtoMapper()
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
    public List<BookDto> getAll() {
        return jdbc.query(
                "select b.id, b.author_id, b.genre_id, b.name, g.name as genre_name, a.first_name, a.last_name\n" +
                    "from book b\n" +
                    "join genre g on g.id = b.genre_id\n" +
                    "join author a on a.id = b.author_id",
                Collections.emptyMap(),
                new BookDtoMapper()
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

    private static class BookDtoMapper implements RowMapper<BookDto> {

        @Override
        public BookDto mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book(
                    resultSet.getLong("id"),
                    resultSet.getLong("author_id"),
                    resultSet.getLong("genre_id"),
                    resultSet.getString("name")
            );
            Genre genre = new Genre(
                    resultSet.getLong("genre_id"),
                    resultSet.getString("genre_name")
            );
            Author author = new Author(
                    resultSet.getLong("author_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")
            );
            return new BookDto(book, author, genre);
        }
    }
}
