package ru.otus.shurupov.spring.authorization.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.shurupov.spring.authorization.service.AuthorService;
import ru.otus.shurupov.spring.authorization.service.BookCommentService;
import ru.otus.shurupov.spring.authorization.service.BookService;
import ru.otus.shurupov.spring.authorization.service.GenreService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SummaryController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final BookCommentService bookCommentService;

    @GetMapping("/api/summary")
    public Map<String, Long> index() {
        return Map.of(
                "books", bookService.count(),
                "genres", genreService.count(),
                "authors", authorService.count(),
                "comments", bookCommentService.count()
        );
    }

}
