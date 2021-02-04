package ru.otus.shurupov.spring.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.springdata.repository.BookCommentRepository;
import ru.otus.shurupov.spring.springdata.repository.BookRepository;
import ru.otus.shurupov.spring.springdata.domain.Book;
import ru.otus.shurupov.spring.springdata.domain.BookComment;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;
    private final BookRepository bookRepository;
    private final TableRenderer tableRenderer;
    private final BookService bookService;

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
    public void insert(long bookId, String comment) {
        BookComment bookComment = new BookComment();
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found!"));
        bookComment.setBook(book);
        bookComment.setText(comment);
        bookCommentRepository.save(bookComment);
    }

    @Override
    public List<BookComment> getAll() {
        return bookCommentRepository.findAll();
    }

    @Override
    @Transactional
    public void removeById(long id) {
        bookCommentRepository.deleteById(id);
    }

}
