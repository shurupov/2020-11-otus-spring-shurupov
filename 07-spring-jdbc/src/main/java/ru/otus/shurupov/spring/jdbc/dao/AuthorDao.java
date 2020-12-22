package ru.otus.shurupov.spring.jdbc.dao;

import ru.otus.shurupov.spring.jdbc.domain.Author;

import java.util.List;
import java.util.Map;

public interface AuthorDao {
    int count();
    Author getById(Long id);
    void insert(Author book);
    List<Author> getAll();
    Map<Long, Author> getUsed();
}
