package ru.otus.shurupov.spring.springdata.service;

import ru.otus.shurupov.spring.springdata.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentService {
    long count();
    Optional<BookComment> getById(long id);
    void insert(long bookId, String comment);
    List<BookComment> getAll();
    void removeById(long id);
    void update(long id, String comment);

    void displayList();
    void displayById(Long id);
}
