package ru.otus.shurupov.spring.springspa.service;

import ru.otus.shurupov.spring.springspa.domain.BookComment;
import ru.otus.shurupov.spring.springspa.domain.dto.BookCommentRequest;

import java.util.List;

public interface BookCommentService {
    long count();
    BookComment getById(long id);
    void create(BookCommentRequest bookCommentRequest);
    void update(BookCommentRequest bookCommentRequest);
    List<BookComment> getAll();
    void removeById(long id);
}
