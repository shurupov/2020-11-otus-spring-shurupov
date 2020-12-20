package ru.otus.shurupov.spring.jdbc.service;

import ru.otus.shurupov.spring.jdbc.domain.dto.BookDto;

import java.util.List;

public interface BookService {
    int count();
    BookDto getById(Long id);
    void insert(String name, Long authorId, Long genreId);
    List<BookDto> getAll();
    void removeById(Long id);
    void update(Long id, String name, Long authorId, Long genreId);
}
