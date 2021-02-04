package ru.otus.shurupov.spring.springdata.service;

import liquibase.pro.packaged.B;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.springdata.domain.dto.BookRequest;
import ru.otus.shurupov.spring.springdata.repository.AuthorRepository;
import ru.otus.shurupov.spring.springdata.repository.BookRepository;
import ru.otus.shurupov.spring.springdata.repository.GenreRepository;
import ru.otus.shurupov.spring.springdata.domain.Author;
import ru.otus.shurupov.spring.springdata.domain.Book;
import ru.otus.shurupov.spring.springdata.domain.Genre;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final TableRenderer tableRenderer;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void insert(String name, Long authorId, Long genreId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new RuntimeException("Genre not found"));
        bookRepository.save(new Book(name, author, Collections.singletonList(genre)));
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
    public void update(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow();
        save(book, bookRequest);
    }

    @Override
    public void create(BookRequest bookRequest) {
        save(new Book(), bookRequest);
    }

    @Override
    public String getBookCaption(Book book) {
        return String.format("%s (%s)", book.getName(), book.getId());
    }

    private void save(Book book, BookRequest bookRequest) {
        book.setName(bookRequest.getName());
        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElseThrow();
        book.setAuthor(author);
        List<Genre> genres = genreRepository.findAllById(bookRequest.getGenreIds());
        book.setGenres(genres);
        bookRepository.save(book);
    }
}
