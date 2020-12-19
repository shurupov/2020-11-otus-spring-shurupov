package ru.otus.shurupov.spring.jdbc.dao;

import ru.otus.shurupov.spring.jdbc.domain.Book;

public interface BookDao {
    int count();
    Book getById(Long id);
    void insert(Book book);
}
