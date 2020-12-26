package ru.otus.shurupov.spring.jdbc.service;

import ru.otus.shurupov.spring.jdbc.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    long count();
    Optional<Book> getById(Long id);
    void insert(String name, Long authorId, Long genreId);
    List<Book> getAll();
    void removeById(Long id);
    void updateName(Long id, String name);
}
