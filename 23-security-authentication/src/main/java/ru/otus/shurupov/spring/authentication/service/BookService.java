package ru.otus.shurupov.spring.authentication.service;

import ru.otus.shurupov.spring.authentication.domain.Book;
import ru.otus.shurupov.spring.authentication.domain.dto.BookRequest;

import java.util.List;

public interface BookService {
    long count();
    Book getById(Long id);
    List<Book> getAll();
    void removeById(Long id);
    Book update(Long id, BookRequest bookRequest);
    Book create(BookRequest bookRequest);

    String getBookCaption(Book book);
}
