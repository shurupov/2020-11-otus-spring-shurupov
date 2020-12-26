package ru.otus.shurupov.spring.jdbc.dao;

import ru.otus.shurupov.spring.jdbc.domain.Book;
import ru.otus.shurupov.spring.jdbc.domain.dto.BookDto;

import java.util.List;

public interface BookDao {
    int count();
    BookDto getById(Long id);
    void insert(Book book);
    List<BookDto> getAll();
    void removeById(Long id);
    void update(Book book);
}
