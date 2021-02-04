package ru.otus.shurupov.spring.springdata.service;

import ru.otus.shurupov.spring.springdata.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentService {
    long count();
    BookComment getById(long id);
    void insert(long bookId, String comment);
    List<BookComment> getAll();
    void removeById(long id);
}
