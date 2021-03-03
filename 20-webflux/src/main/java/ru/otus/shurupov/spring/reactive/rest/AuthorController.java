package ru.otus.shurupov.spring.reactive.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.shurupov.spring.reactive.domain.Author;

import ru.otus.shurupov.spring.reactive.domain.dto.AuthorDto;
import ru.otus.shurupov.spring.reactive.repository.AuthorRepository;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;

    @GetMapping("/api/v1/authors")
    public Flux<Author> getAll() {
        return authorRepository.findAll();
    }

    @GetMapping("/api/v1/authors/{id}")
    public Mono<Author> get(@PathVariable String id) {
        return authorRepository.findById(id);
    }

    @PostMapping("/api/v1/authors")
    public Mono<Author> create(@RequestBody AuthorDto author) {
        return authorRepository.save(new Author(author));
    }

    @PutMapping("/api/v1/authors/{id}")
    public Mono<Author> update(@PathVariable String id, @RequestBody AuthorDto author) {
        return authorRepository.findById(id)
                .flatMap(author1 -> {
                    author1.setFirstName(author.getFirstName());
                    author1.setLastName(author.getLastName());
                    return authorRepository.save(author1);
                });
    }

    @DeleteMapping("/api/v1/authors/{id}")
    public Mono<Void> remove(@PathVariable String id) {
        return authorRepository.deleteById(id);
    }
}
