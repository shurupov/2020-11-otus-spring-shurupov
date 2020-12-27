package ru.otus.shurupov.spring.jpa.dao;

import ru.otus.shurupov.spring.jpa.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    long count();
    Optional<Genre> getById(Long id);
    void insert(Genre book);
    List<Genre> getAll();
}
