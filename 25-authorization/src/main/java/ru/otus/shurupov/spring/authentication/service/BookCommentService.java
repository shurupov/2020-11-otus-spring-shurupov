package ru.otus.shurupov.spring.authentication.service;

import ru.otus.shurupov.spring.authentication.domain.BookComment;
import ru.otus.shurupov.spring.authentication.domain.dto.BookCommentDto;

import java.util.List;

public interface BookCommentService {
    long count();
    BookComment getById(long id);
    BookComment create(Long bookId, BookCommentDto bookCommentDto);
    BookComment update(BookCommentDto bookCommentDto);
    List<BookComment> getBookComments(Long bookId);
    void removeById(long id);
}
