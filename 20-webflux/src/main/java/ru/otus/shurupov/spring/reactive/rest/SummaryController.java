package ru.otus.shurupov.spring.reactive.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.shurupov.spring.reactive.domain.Book;
import ru.otus.shurupov.spring.reactive.repository.AuthorRepository;
import ru.otus.shurupov.spring.reactive.repository.BookRepository;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SummaryController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @GetMapping("/api/v1/summary")
    public Mono<Map<String, Long>> getAll() {
        return bookRepository.count().flatMap(booksCount ->
            authorRepository.count().flatMap(authorsCount ->
                Mono.just(
                        Map.of(
                                "books", booksCount,
                                "authors", authorsCount
                        )
                )
            )
        );
    }

}
