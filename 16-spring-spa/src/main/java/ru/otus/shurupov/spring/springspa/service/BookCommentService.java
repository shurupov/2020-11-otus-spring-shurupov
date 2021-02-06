package ru.otus.shurupov.spring.springspa.service;

import ru.otus.shurupov.spring.springspa.domain.BookComment;
import ru.otus.shurupov.spring.springspa.domain.dto.BookCommentDto;

import java.util.List;

public interface BookCommentService {
    long count();
    BookComment getById(long id);
    BookComment create(BookCommentDto bookCommentDto);
    BookComment update(BookCommentDto bookCommentDto);
    List<BookComment> getAll();
    void removeById(long id);
}
