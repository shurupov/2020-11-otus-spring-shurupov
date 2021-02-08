package ru.otus.shurupov.spring.springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.shurupov.spring.springmvc.domain.BookComment;
import ru.otus.shurupov.spring.springmvc.domain.dto.BookCommentRequest;
import ru.otus.shurupov.spring.springmvc.domain.dto.BreadCrumb;
import ru.otus.shurupov.spring.springmvc.service.BookCommentService;
import ru.otus.shurupov.spring.springmvc.service.BookService;

import java.util.List;

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
    public String commentAddPost(BookCommentRequest bookCommentRequest) {
        bookCommentService.create(bookCommentRequest);
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
    public String commentEditPost(@PathVariable Long id, BookCommentRequest bookCommentRequest) {
        bookCommentRequest.setId(id);
        bookCommentService.update(bookCommentRequest);
        return "redirect:/comments";
    }

    @GetMapping("/comments/{id}/remove")
    public String bookRemove(@PathVariable Long id) {
        bookCommentService.removeById(id);
        return "redirect:/comments";
    }
}
