package ru.otus.shurupov.spring.springspa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.shurupov.spring.springspa.domain.Author;
import ru.otus.shurupov.spring.springspa.service.AuthorService;

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
    public Author authorAddPost(@RequestBody Author author) {
        author = authorService.save(author);
        return author;
    }

    @GetMapping("/api/authors/{id}")
    public Author bookView(@PathVariable Long id) {
        Author author = authorService.getById(id);
        return author;
    }

    @PutMapping("/api/authors/{id}")
    public Author bookEditPost(@PathVariable Long id, @RequestBody Author author) {
        author.setId(id);
        Author updated = authorService.save(author);
        return updated;
    }

    @DeleteMapping("/api/authors/{id}")
    public void bookRemove(@PathVariable Long id) {
        authorService.removeById(id);
    }
}
