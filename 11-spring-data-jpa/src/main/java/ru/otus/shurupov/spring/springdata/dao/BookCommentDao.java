package ru.otus.shurupov.spring.springdata.dao;

import ru.otus.shurupov.spring.springdata.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentDao {
    long count();
    Optional<BookComment> getById(long id);
    void insert(BookComment comment);
    List<BookComment> getAll();
    void remove(BookComment bookComment);
    void updateById(long id, String comment);
}
