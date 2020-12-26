package ru.otus.shurupov.spring.jdbc.dao;

import ru.otus.shurupov.spring.jdbc.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    long count();
    Optional<Book> getById(Long id);
    void insert(Book book);
    List<Book> getAll();
    void removeById(Long id);
    void updateNameById(long id, String name);
}
