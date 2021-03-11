package ru.otus.shurupov.spring.authorization.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_ADMIN")
    public Author authorAddPost(@RequestBody Author author) {
        author = authorService.save(author);
        return author;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/api/authors/{id}")
    public Author bookView(@PathVariable Long id) {
        Author author = authorService.getById(id);
        return author;
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/api/authors/{id}")
    public Author bookEditPost(@PathVariable Long id, @RequestBody Author author) {
        author.setId(id);
        Author updated = authorService.save(author);
        return updated;
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/api/authors/{id}")
    public void bookRemove(@PathVariable Long id) {
        authorService.removeById(id);
    }
}
