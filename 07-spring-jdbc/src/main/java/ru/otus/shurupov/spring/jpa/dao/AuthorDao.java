package ru.otus.shurupov.spring.jpa.dao;

import ru.otus.shurupov.spring.jpa.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();
    Author getById(Long id);
    void insert(Author book);
    List<Author> getAll();
}
