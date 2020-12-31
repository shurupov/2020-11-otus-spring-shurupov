package ru.otus.shurupov.spring.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.jpa.dao.BookCommentDao;
import ru.otus.shurupov.spring.jpa.domain.Book;
import ru.otus.shurupov.spring.jpa.domain.BookComment;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentDao bookCommentDao;

    @Override
    public long count() {
        return bookCommentDao.count();
    }

    @Override
    public Optional<BookComment> getById(long id) {
        return bookCommentDao.getById(id);
    }

    @Override
    @Transactional
    public void insert(long bookId, String comment) {
        BookComment bookComment = new BookComment();
        Book book = new Book();
        book.setId(bookId);
        bookComment.setBook(book);
        bookComment.setText(comment);
        bookCommentDao.insert(bookComment);
    }

    @Override
    public List<BookComment> getAll() {
        return bookCommentDao.getAll();
    }

    @Override
    public void removeById(long id) {
        bookCommentDao.removeById(id);
    }

    @Override
    public void update(long id, String comment) {
        bookCommentDao.updateById(id, comment);
    }
}
