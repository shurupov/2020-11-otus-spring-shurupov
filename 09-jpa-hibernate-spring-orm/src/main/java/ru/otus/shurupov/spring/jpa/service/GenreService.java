package ru.otus.shurupov.spring.jpa.service;

import ru.otus.shurupov.spring.jpa.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    long count();
    Optional<Genre> getById(Long id);
    void insert(String name);
    List<Genre> getAll();

    void displayList();
    void displayById(Long id);
}
