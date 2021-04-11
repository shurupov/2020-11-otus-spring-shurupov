package ru.otus.shurupov.spring.actuator.service;

import ru.otus.shurupov.spring.actuator.domain.Genre;

import java.util.List;

public interface GenreService {
    long count();
    Genre getById(Long id);
    Genre insert(Genre genre);
    Genre update(Long id, Genre genre);
    void removeById(Long id);

    List<Genre> getAll();
}
