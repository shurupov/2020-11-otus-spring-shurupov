package ru.otus.shurupov.spring.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.shurupov.spring.springmvc.domain.dto.BookRequest;
import ru.otus.shurupov.spring.springmvc.repository.AuthorRepository;
import ru.otus.shurupov.spring.springmvc.repository.BookRepository;
import ru.otus.shurupov.spring.springmvc.repository.GenreRepository;
import ru.otus.shurupov.spring.springmvc.domain.Author;
import ru.otus.shurupov.spring.springmvc.domain.Book;
import ru.otus.shurupov.spring.springmvc.domain.Genre;

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
