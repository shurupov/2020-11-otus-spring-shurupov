package ru.otus.shurupov.spring.jdbc.service;

import ru.otus.shurupov.spring.jdbc.domain.Book;

import java.util.List;

public interface BookService {
    int count();
    Book getById(Long id);
    void insert(Long id, String name);
    List<Book> getAll();
}
