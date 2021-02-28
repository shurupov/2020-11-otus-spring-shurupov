package ru.otus.shurupov.spring.reactive.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.shurupov.spring.reactive.domain.Book;
import ru.otus.shurupov.spring.reactive.domain.dto.BookDto;
import ru.otus.shurupov.spring.reactive.repository.AuthorRepository;
import ru.otus.shurupov.spring.reactive.repository.BookRepository;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @GetMapping("/api/v1/books")
    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }

    @PostMapping("/api/v1/books")
    public Mono<Book> post(@RequestBody BookDto book) {
        return authorRepository.findById(book.getAuthorId())
                .flatMap(author -> {
                    Book book1 = new Book();
                    book1.setAuthor(author);
                    book1.setName(book.getName());
                    book1.setGenres(book.getGenres());
                    book1.setComments(book.getComments());
                    return Mono.just(book1);
                }).flatMap(bookRepository::save);
    }

    @GetMapping("/api/v1/books/{id}")
    public Mono<BookDto> get(@PathVariable String id) {
        return bookRepository.findById(id)
                .flatMap(book -> Mono.just(new BookDto(book)));
    }

    @PutMapping("/api/v1/books/{id}")
    public Mono<Book> update(@PathVariable String id, @RequestBody BookDto book) {
        return bookRepository.findById(id)
                .flatMap(book1 -> {
                    book1.setName(book.getName());
                    book1.setGenres(book.getGenres());
                    book1.setComments(book.getComments());
                    return Mono.just(book1);
                }).flatMap(book1 ->
                        authorRepository.findById(book.getAuthorId())
                        .map(author -> {
                            book1.setAuthor(author);
                            return book1;
                        })
                ).flatMap(bookRepository::save);
    }

    @DeleteMapping("/api/v1/books/{id}")
    public Mono<Void> remove(@PathVariable String id) {
        return bookRepository.deleteById(id);
    }
}
