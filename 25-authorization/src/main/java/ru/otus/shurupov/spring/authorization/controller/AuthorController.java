package ru.otus.shurupov.spring.authorization.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.otus.shurupov.spring.authorization.domain.Author;
import ru.otus.shurupov.spring.authorization.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/api/authors")
    public List<Author> authorList() {
        List<Author> authors = authorService.getAll();
        return authors;
    }

    @PostMapping("/api/authors")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Author add(@RequestBody Author author) {
        author = authorService.save(author);
        return author;
    }

    @GetMapping("/api/authors/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Author get(@PathVariable Long id) {
        Author author = authorService.getById(id);
        return author;
    }

    @PutMapping("/api/authors/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Author update(@PathVariable Long id, @RequestBody Author author) {
        author.setId(id);
        Author updated = authorService.save(author);
        return updated;
    }

    @DeleteMapping("/api/authors/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void remove(@PathVariable Long id) {
        authorService.removeById(id);
    }
}
