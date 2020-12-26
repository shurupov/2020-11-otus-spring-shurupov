package ru.otus.shurupov.spring.jdbc.service;

import ru.otus.shurupov.spring.jdbc.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    long count();
    Optional<Author> getById(Long id);
    void insert(String firstName, String lastName);
    List<Author> getAll();
}
