package ru.otus.shurupov.spring.springdata.service;

import ru.otus.shurupov.spring.springdata.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    long count();
    Author getById(Long id);
    void save(Author author);
    List<Author> getAll();
    void removeById(Long id);
}
