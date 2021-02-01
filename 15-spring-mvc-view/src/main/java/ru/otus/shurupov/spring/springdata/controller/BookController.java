package ru.otus.shurupov.spring.springdata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.shurupov.spring.springdata.service.BookService;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public String bookList(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books/list";
    }
}
