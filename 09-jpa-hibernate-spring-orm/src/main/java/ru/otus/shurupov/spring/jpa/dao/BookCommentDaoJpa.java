package ru.otus.shurupov.spring.jpa.dao;

import org.springframework.stereotype.Repository;
import ru.otus.shurupov.spring.jpa.domain.BookComment;

import java.util.List;
import java.util.Optional;

@Repository
public class BookCommentDaoJpa implements BookCommentDao {
    @Override
    public long count() {
        return 0;
    }

    @Override
    public Optional<BookComment> getById(long id) {
        return Optional.empty();
    }

    @Override
    public void insert(BookComment comment) {

    }

    @Override
    public List<BookComment> getAll() {
        return null;
    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public void updateById(long id, String comment) {

    }
}
