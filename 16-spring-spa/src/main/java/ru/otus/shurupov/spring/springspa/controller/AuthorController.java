package ru.otus.shurupov.spring.springspa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.shurupov.spring.springspa.domain.Author;
import ru.otus.shurupov.spring.springspa.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> authorList() {
        List<Author> authors = authorService.getAll();
        return authors;
    }

    @PostMapping("/authors")
    public Author authorAddPost(Author author) {
        author = authorService.save(author);
        return author;
    }

    @GetMapping("/authors/{id}")
    public Author bookView(@PathVariable Long id, Model model) {
        Author author = authorService.getById(id);
        return author;
    }

    @PutMapping("/authors/{id}")
    public Author bookEditPost(@PathVariable Long id, Author author) {
        author.setId(id);
        Author updated = authorService.save(author);
        return updated;
    }

    @DeleteMapping("/authors/{id}")
    public void bookRemove(@PathVariable Long id) {
        authorService.removeById(id);
    }
}
