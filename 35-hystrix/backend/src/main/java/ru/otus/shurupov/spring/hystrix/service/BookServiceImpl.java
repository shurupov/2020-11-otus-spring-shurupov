package ru.otus.shurupov.spring.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;
import ru.otus.shurupov.spring.hystrix.domain.dto.BookRequest;
import ru.otus.shurupov.spring.hystrix.repository.AuthorRepository;
import ru.otus.shurupov.spring.hystrix.repository.BookRepository;
import ru.otus.shurupov.spring.hystrix.repository.GenreRepository;
import ru.otus.shurupov.spring.hystrix.domain.Author;
import ru.otus.shurupov.spring.hystrix.domain.Book;
import ru.otus.shurupov.spring.hystrix.domain.Genre;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    private Map<Long, Book> cache = Collections.emptyMap();

    @Override
    @HystrixCommand(commandKey="getBooks", fallbackMethod="countFallback")
    public long count() {
        return bookRepository.count();
    }

    @Override
    @HystrixCommand(commandKey="getBooks", fallbackMethod="getByIdFromCache")
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    @HystrixCommand(commandKey="getBooks", fallbackMethod="getAllCached")
    public Collection<Book> getAll() {
        List<Book> result = bookRepository.findAll(Sort.by("id"));
        updateCache(result);
        return result;
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey="getBooks", fallbackMethod="throwException")
    public void removeById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey="getBooks", fallbackMethod="throwException")
    public Book update(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow();
        return save(book, bookRequest);
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey="getBooks", fallbackMethod="throwException")
    public Book create(BookRequest bookRequest) {
        return save(new Book(), bookRequest);
    }

    @Override
    public String getBookCaption(Book book) {
        return String.format("%s (%s)", book.getName(), book.getId());
    }

    private Book save(Book book, BookRequest bookRequest) {
        book.setName(bookRequest.getName());
        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElseThrow();
        book.setAuthor(author);
        List<Genre> genres = genreRepository.findAllById(bookRequest.getGenres());
        book.setGenres(genres);
        return bookRepository.save(book);
    }

    public void updateCache(Collection<Book> source) {
        cache = source.stream().collect(Collectors.toMap(
                Book::getId, book -> book
        ));
    }

    public Collection<Book> getAllCached() {
        return cache.values();
    }

    public Book getByIdFromCache(Long id) {
        return cache.get(id);
    }

    public void throwException() {
        throw new ServerErrorException("Temporary server problems", new RuntimeException());
    }

    public long countFallback() {
        return 0L;
    }
}
