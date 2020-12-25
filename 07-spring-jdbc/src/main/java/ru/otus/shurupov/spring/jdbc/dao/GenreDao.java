package ru.otus.shurupov.spring.jdbc.dao;

import ru.otus.shurupov.spring.jdbc.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    Genre getById(Long id);
    void insert(Genre book);
    List<Genre> getAll();
}
