package ru.otus.shurupov.spring.jpa.service;

import ru.otus.shurupov.spring.jpa.domain.Author;

import java.util.List;

public interface AuthorService {
    int count();
    Author getById(Long id);
    void insert(String firstName, String lastName);
    List<Author> getAll();
}
