package ru.otus.shurupov.spring.springdata.dao;

import ru.otus.shurupov.spring.springdata.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    long count();
    Optional<Author> getById(Long id);
    void insert(Author book);
    List<Author> getAll();
}
