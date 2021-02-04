package ru.otus.shurupov.spring.springdata.service;

import ru.otus.shurupov.spring.springdata.domain.Book;
import ru.otus.shurupov.spring.springdata.domain.dto.BookRequest;

import java.util.List;

public interface BookService {
    long count();
    Book getById(Long id);
    void insert(String name, Long authorId, Long genreId);
    List<Book> getAll();
    void removeById(Long id);
    void update(Long id, BookRequest bookRequest);
    void create(BookRequest bookRequest);

    String getBookCaption(Book book);
}
