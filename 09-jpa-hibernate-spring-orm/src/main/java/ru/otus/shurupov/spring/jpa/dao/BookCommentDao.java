package ru.otus.shurupov.spring.jpa.dao;

import ru.otus.shurupov.spring.jpa.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentDao {
    long count();
    Optional<BookComment> getById(long id);
    void insert(BookComment comment);
    List<BookComment> getAll();
    void removeById(long id);
    void updateById(long id, String comment);
}
