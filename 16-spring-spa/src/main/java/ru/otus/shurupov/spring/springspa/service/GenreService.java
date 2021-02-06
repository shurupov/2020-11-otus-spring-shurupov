package ru.otus.shurupov.spring.springspa.service;

import ru.otus.shurupov.spring.springspa.domain.Genre;

import java.util.List;

public interface GenreService {
    long count();
    Genre getById(Long id);
    void insert(String name);
    void update(Long id, String name);
    void removeById(Long id);

    List<Genre> getAll();
}
