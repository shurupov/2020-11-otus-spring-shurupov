package ru.otus.shurupov.spring.springspa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.springspa.domain.dto.BookCommentDto;
import ru.otus.shurupov.spring.springspa.repository.BookCommentRepository;
import ru.otus.shurupov.spring.springspa.repository.BookRepository;
import ru.otus.shurupov.spring.springspa.domain.Book;
import ru.otus.shurupov.spring.springspa.domain.BookComment;

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
    public BookComment create(BookCommentDto bookCommentDto) {
        return save(new BookComment(), bookCommentDto);
    }

    @Override
    public BookComment update(BookCommentDto bookCommentDto) {
        BookComment bookComment = bookCommentRepository.findById(bookCommentDto.getId()).orElseThrow();
        return save(bookComment, bookCommentDto);
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

    private BookComment save(BookComment bookComment, BookCommentDto bookCommentDto) {
        bookComment.setText(bookCommentDto.getText());
        Book book = bookRepository.findById(bookCommentDto.getBookId()).orElseThrow();
        bookComment.setBook(book);
        return bookCommentRepository.save(bookComment);
    }
}
