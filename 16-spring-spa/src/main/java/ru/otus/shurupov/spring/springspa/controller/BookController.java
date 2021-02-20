package ru.otus.shurupov.spring.springspa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.shurupov.spring.springspa.domain.Book;
import ru.otus.shurupov.spring.springspa.domain.Genre;
import ru.otus.shurupov.spring.springspa.domain.dto.BookResponse;
import ru.otus.shurupov.spring.springspa.domain.dto.BookRequest;
import ru.otus.shurupov.spring.springspa.domain.dto.PoorBookResponse;
import ru.otus.shurupov.spring.springspa.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/books")
    public List<BookResponse> bookList() {
        List<Book> books = bookService.getAll();
        List<BookResponse> bookResponses = books.stream()
                .map(this::map).collect(Collectors.toList());
        return bookResponses;
    }

    @PostMapping("/api/books")
    public BookResponse bookAddPost(@RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = map(bookService.create(bookRequest));
        return bookResponse;
    }

    @PutMapping("/api/books/{id}")
    public BookResponse bookEdit(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        Book book = bookService.update(id, bookRequest);
        BookResponse bookResponse = map(book);
        return bookResponse;
    }

    @GetMapping("/api/books/{id}")
    public PoorBookResponse bookView(@PathVariable Long id) {
        Book book = bookService.getById(id);
        PoorBookResponse bookResponse = poorMap(book);
        return bookResponse;
    }

    @DeleteMapping("/api/books/{id}")
    public void bookRemove(@PathVariable Long id) {
        bookService.removeById(id);
    }

    private BookResponse map(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName())
                .genres(book.getGenres().stream().map(Genre::getName).collect(Collectors.joining(", ")))
                .build();
    }

    private PoorBookResponse poorMap(Book book) {
        return PoorBookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .authorId(book.getAuthor().getId())
                .genres(book.getGenres().stream().map(Genre::getId).collect(Collectors.toList()))
                .build();
    }
}
