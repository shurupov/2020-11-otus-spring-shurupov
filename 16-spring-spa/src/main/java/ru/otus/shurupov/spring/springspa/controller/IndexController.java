package ru.otus.shurupov.spring.springspa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.shurupov.spring.springspa.service.AuthorService;
import ru.otus.shurupov.spring.springspa.service.BookCommentService;
import ru.otus.shurupov.spring.springspa.service.BookService;
import ru.otus.shurupov.spring.springspa.service.GenreService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final BookCommentService bookCommentService;

    @GetMapping("/")
    public Map<String, Long> index(Model model) {
        return Map.of(
                "books", bookService.count(),
                "genres", genreService.count(),
                "authors", authorService.count(),
                "comments", bookCommentService.count()
        );
    }

}
