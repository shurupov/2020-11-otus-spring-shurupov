package ru.otus.shurupov.spring.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;
import ru.otus.shurupov.spring.hystrix.domain.dto.BookCommentDto;
import ru.otus.shurupov.spring.hystrix.repository.BookCommentRepository;
import ru.otus.shurupov.spring.hystrix.repository.BookRepository;
import ru.otus.shurupov.spring.hystrix.domain.Book;
import ru.otus.shurupov.spring.hystrix.domain.BookComment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;
    private final BookRepository bookRepository;

    @Override
    @HystrixCommand(commandKey="getComments", fallbackMethod="throwException")
    public long count() {
        return bookCommentRepository.count();
    }

    @Override
    @HystrixCommand(commandKey="getComments", fallbackMethod="throwException")
    public BookComment getById(long id) {
        return bookCommentRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey="getComments", fallbackMethod="throwException")
    public BookComment create(Long bookId, BookCommentDto bookCommentDto) {
        BookComment bookComment = new BookComment();
        bookComment.setText(bookCommentDto.getText());
        Book book = bookRepository.findById(bookId).orElseThrow();
        bookComment.setBook(book);
        return bookCommentRepository.save(bookComment);
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey="getComments", fallbackMethod="throwException")
    public BookComment update(BookCommentDto bookCommentDto) {
        BookComment bookComment = bookCommentRepository.findById(bookCommentDto.getId()).orElseThrow();
        bookComment.setText(bookCommentDto.getText());
        return bookCommentRepository.save(bookComment);
    }

    @Override
    @HystrixCommand(commandKey="getComments", fallbackMethod="throwException")
    public List<BookComment> getBookComments(Long bookId) {
        return bookCommentRepository.getAllByBookId(bookId, Sort.by("id"));
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey="getComments", fallbackMethod="throwException")
    public void removeById(long id) {
        bookCommentRepository.deleteById(id);
    }

    public void throwException() {
        throw new ServerErrorException("Temporary server problems", new RuntimeException());
    }
}
