package ru.otus.shurupov.spring.authorization.service;

import ru.otus.shurupov.spring.authorization.domain.Book;
import ru.otus.shurupov.spring.authorization.domain.dto.BookRequest;

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
