package ru.otus.shurupov.spring.springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.shurupov.spring.springmvc.service.AuthorService;
import ru.otus.shurupov.spring.springmvc.service.BookCommentService;
import ru.otus.shurupov.spring.springmvc.service.BookService;
import ru.otus.shurupov.spring.springmvc.service.GenreService;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final BookCommentService bookCommentService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookService.count());
        model.addAttribute("genres", genreService.count());
        model.addAttribute("authors", authorService.count());
        model.addAttribute("comments", bookCommentService.count());
        model.addAttribute("breadcrumbs", Collections.emptyList());
        return "index";
    }

}
