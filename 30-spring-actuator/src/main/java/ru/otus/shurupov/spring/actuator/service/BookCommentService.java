package ru.otus.shurupov.spring.actuator.service;

import ru.otus.shurupov.spring.actuator.domain.BookComment;
import ru.otus.shurupov.spring.actuator.domain.dto.BookCommentDto;

import java.util.List;

public interface BookCommentService {
    long count();
    BookComment getById(long id);
    BookComment create(Long bookId, BookCommentDto bookCommentDto);
    BookComment update(BookCommentDto bookCommentDto);
    List<BookComment> getBookComments(Long bookId);
    void removeById(long id);
}
