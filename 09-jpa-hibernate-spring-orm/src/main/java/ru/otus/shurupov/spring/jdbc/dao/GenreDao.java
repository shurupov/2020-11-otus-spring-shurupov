package ru.otus.shurupov.spring.jdbc.dao;

import ru.otus.shurupov.spring.jdbc.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    long count();
    Optional<Genre> getById(Long id);
    void insert(Genre book);
    List<Genre> getAll();
}
