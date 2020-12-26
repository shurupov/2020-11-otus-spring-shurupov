package ru.otus.shurupov.spring.jdbc.service;

import ru.otus.shurupov.spring.jdbc.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    long count();
    Optional<Genre> getById(Long id);
    void insert(String name);
    List<Genre> getAll();
}
