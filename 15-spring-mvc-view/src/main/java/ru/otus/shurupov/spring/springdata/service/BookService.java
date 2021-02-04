package ru.otus.shurupov.spring.springdata.service;

import org.springframework.data.domain.Sort;
import ru.otus.shurupov.spring.springdata.domain.Book;
import ru.otus.shurupov.spring.springdata.domain.dto.BookRequest;

import java.util.List;
import java.util.Optional;

public interface BookService {
    long count();
    Book getById(Long id);
    void insert(String name, Long authorId, Long genreId);
    List<Book> getAll();
    void removeById(Long id);
    void update(Long id, BookRequest bookRequest);

    String getBookCaption(Book book);
}
