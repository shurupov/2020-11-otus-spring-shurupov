package ru.otus.shurupov.spring.hystrix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.hystrix.domain.dto.BookRequest;
import ru.otus.shurupov.spring.hystrix.repository.AuthorRepository;
import ru.otus.shurupov.spring.hystrix.repository.BookRepository;
import ru.otus.shurupov.spring.hystrix.repository.GenreRepository;
import ru.otus.shurupov.spring.hystrix.domain.Author;
import ru.otus.shurupov.spring.hystrix.domain.Book;
import ru.otus.shurupov.spring.hystrix.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll(Sort.by("id"));
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book update(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow();
        return save(book, bookRequest);
    }

    @Override
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
}
