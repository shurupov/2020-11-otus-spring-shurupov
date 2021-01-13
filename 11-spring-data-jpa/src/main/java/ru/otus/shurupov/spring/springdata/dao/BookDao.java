package ru.otus.shurupov.spring.springdata.dao;

import ru.otus.shurupov.spring.springdata.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    long count();
    Optional<Book> getById(Long id);
    void insert(Book book);
    List<Book> getAll();
    void remove(Book book);
    void updateNameById(long id, String name);
}
