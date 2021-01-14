package ru.otus.shurupov.spring.jpa.dao;

import ru.otus.shurupov.spring.jpa.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    Genre getById(Long id);
    void insert(Genre book);
    List<Genre> getAll();
}
