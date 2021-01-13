package ru.otus.shurupov.spring.springdata.repository;

import ru.otus.shurupov.spring.springdata.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    long count();
    Optional<Genre> getById(Long id);
    void insert(Genre book);
    List<Genre> getAll();
}
