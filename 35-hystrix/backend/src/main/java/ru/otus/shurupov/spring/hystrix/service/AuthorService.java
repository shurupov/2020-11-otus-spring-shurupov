package ru.otus.shurupov.spring.hystrix.service;

import ru.otus.shurupov.spring.hystrix.domain.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorService {
    long count();
    Author getById(Long id);
    Author save(Author author);
    Collection<Author> getAll();
    void removeById(Long id);
}
