package ru.otus.shurupov.spring.actuator.service;

import ru.otus.shurupov.spring.actuator.domain.Author;

import java.util.List;

public interface AuthorService {
    long count();
    Author getById(Long id);
    Author save(Author author);
    List<Author> getAll();
    void removeById(Long id);
}
