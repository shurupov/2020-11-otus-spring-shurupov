package ru.otus.shurupov.spring.jdbc.dao;

import ru.otus.shurupov.spring.jdbc.domain.Book;

import java.util.List;

public interface BookDao {
    int count();
    Book getById(Long id);
    void insert(Book book);
    List<Book> getAll();
    void removeById(Long id);
    void update(Book book);
}
