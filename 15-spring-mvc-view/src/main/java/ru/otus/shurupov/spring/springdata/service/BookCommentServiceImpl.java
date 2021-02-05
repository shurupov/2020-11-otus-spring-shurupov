package ru.otus.shurupov.spring.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.springdata.domain.dto.BookCommentRequest;
import ru.otus.shurupov.spring.springdata.repository.BookCommentRepository;
import ru.otus.shurupov.spring.springdata.repository.BookRepository;
import ru.otus.shurupov.spring.springdata.domain.Book;
import ru.otus.shurupov.spring.springdata.domain.BookComment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;
    private final BookRepository bookRepository;

    @Override
    public long count() {
        return bookCommentRepository.count();
    }

    @Override
    public BookComment getById(long id) {
        return bookCommentRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void create(BookCommentRequest bookCommentRequest) {
        save(new BookComment(), bookCommentRequest);
    }

    @Override
    public void update(BookCommentRequest bookCommentRequest) {
        BookComment bookComment = bookCommentRepository.findById(bookCommentRequest.getId()).orElseThrow();
        save(bookComment, bookCommentRequest);
    }

    @Override
    public List<BookComment> getAll() {
        return bookCommentRepository.findAll(Sort.by("id"));
    }

    @Override
    @Transactional
    public void removeById(long id) {
        bookCommentRepository.deleteById(id);
    }

    private void save(BookComment bookComment, BookCommentRequest bookCommentRequest) {
        bookComment.setText(bookCommentRequest.getText());
        Book book = bookRepository.findById(bookCommentRequest.getBookId()).orElseThrow();
        bookComment.setBook(book);
        bookCommentRepository.save(bookComment);
    }
}
