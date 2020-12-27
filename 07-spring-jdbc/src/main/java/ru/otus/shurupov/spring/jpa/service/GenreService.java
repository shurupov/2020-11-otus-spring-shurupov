package ru.otus.shurupov.spring.jpa.service;

import ru.otus.shurupov.spring.jpa.domain.Genre;

import java.util.List;

public interface GenreService {
    int count();
    Genre getById(Long id);
    void insert(String name);
    List<Genre> getAll();
}
