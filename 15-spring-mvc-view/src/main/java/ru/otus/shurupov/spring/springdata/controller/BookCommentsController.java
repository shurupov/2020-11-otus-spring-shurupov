package ru.otus.shurupov.spring.springdata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.shurupov.spring.springdata.domain.Book;
import ru.otus.shurupov.spring.springdata.domain.BookComment;
import ru.otus.shurupov.spring.springdata.domain.Genre;
import ru.otus.shurupov.spring.springdata.domain.dto.BookRequest;
import ru.otus.shurupov.spring.springdata.domain.dto.BreadCrumb;
import ru.otus.shurupov.spring.springdata.service.BookCommentService;
import ru.otus.shurupov.spring.springdata.service.BookService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BookCommentsController {

    private final BookCommentService bookCommentService;
    private final BookService bookService;

    @GetMapping("/comments")
    public String bookList(Model model) {
        List<BookComment> comments = bookCommentService.getAll();
        model.addAttribute("comments", comments);
        model.addAttribute("breadcrumbs", List.of(new BreadCrumb("/comments", "Comments")));
        return "comments/list";
    }

    @PostMapping("/comments/add")
    public String commentAddPost(BookRequest bookRequest) {
        //bookCommentService.create(bookRequest);
        return "redirect:/comments";
    }

    @GetMapping("/comments/add")
    public String commentAddView(Model model) {
        BookComment bookComment = new BookComment();
        model.addAttribute("comment", bookComment);
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("breadcrumbs",
                List.of(
                        new BreadCrumb("/comments", "Comments"),
                        new BreadCrumb("/comments/add", "Add Comment")
                )
        );
        return "comments/add";
    }

    @GetMapping("/comments/{id}")
    public String commentView(@PathVariable Long id, Model model) {
        BookComment bookComment = bookCommentService.getById(id);
        model.addAttribute("comment", bookComment);
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("breadcrumbs",
                List.of(
                        new BreadCrumb("/comments", "Comments"),
                        new BreadCrumb("/comments", bookComment.getText())
                )
        );
        return "comments/edit";
    }

    @PostMapping("/comments/{id}")
    public String commentEditPost(@PathVariable Long id, BookRequest bookRequest) {
        //bookCommentService.update(id, bookRequest);
        return "redirect:/comments";
    }

    @GetMapping("/comments/{id}/remove")
    public String bookRemove(@PathVariable Long id) {
        bookCommentService.removeById(id);
        return "redirect:/comments";
    }
}
