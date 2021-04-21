package ru.otus.shurupov.spring.hystrix.service;

import ru.otus.shurupov.spring.hystrix.domain.Genre;

import java.util.List;

public interface GenreService {
    long count();
    Genre getById(Long id);
    Genre insert(Genre genre);
    Genre update(Long id, Genre genre);
    void removeById(Long id);

    List<Genre> getAll();
}
