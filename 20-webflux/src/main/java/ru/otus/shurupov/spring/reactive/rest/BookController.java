package ru.otus.shurupov.spring.reactive.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.shurupov.spring.reactive.domain.Book;
import ru.otus.shurupov.spring.reactive.repository.BookRepository;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/api/1/books/{id}")
    public Mono<Book> byId(@PathVariable String id) {
        return bookRepository.findById(id);
    }

    @GetMapping("/api/1/books")
    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }
}
