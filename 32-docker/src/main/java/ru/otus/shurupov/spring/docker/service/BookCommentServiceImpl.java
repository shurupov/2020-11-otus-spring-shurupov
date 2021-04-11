package ru.otus.shurupov.spring.docker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.docker.domain.dto.BookCommentDto;
import ru.otus.shurupov.spring.docker.repository.BookCommentRepository;
import ru.otus.shurupov.spring.docker.repository.BookRepository;
import ru.otus.shurupov.spring.docker.domain.Book;
import ru.otus.shurupov.spring.docker.domain.BookComment;

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
    public BookComment create(Long bookId, BookCommentDto bookCommentDto) {
        BookComment bookComment = new BookComment();
        bookComment.setText(bookCommentDto.getText());
        Book book = bookRepository.findById(bookId).orElseThrow();
        bookComment.setBook(book);
        return bookCommentRepository.save(bookComment);
    }

    @Override
    @Transactional
    public BookComment update(BookCommentDto bookCommentDto) {
        BookComment bookComment = bookCommentRepository.findById(bookCommentDto.getId()).orElseThrow();
        bookComment.setText(bookCommentDto.getText());
        return bookCommentRepository.save(bookComment);
    }

    @Override
    public List<BookComment> getBookComments(Long bookId) {
        return bookCommentRepository.getAllByBookId(bookId, Sort.by("id"));
    }

    @Override
    @Transactional
    public void removeById(long id) {
        bookCommentRepository.deleteById(id);
    }
}
