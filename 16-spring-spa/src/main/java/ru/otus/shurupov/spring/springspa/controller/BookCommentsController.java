package ru.otus.shurupov.spring.springspa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.shurupov.spring.springspa.domain.BookComment;
import ru.otus.shurupov.spring.springspa.domain.dto.BookCommentDto;
import ru.otus.shurupov.spring.springspa.service.BookCommentService;
import ru.otus.shurupov.spring.springspa.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookCommentsController {

    private final BookCommentService bookCommentService;
    private final BookService bookService;

    @GetMapping("/comments")
    public List<BookComment> bookList() {
        List<BookComment> comments = bookCommentService.getAll();
        return comments;
    }

    @PostMapping("/comments")
    public BookCommentDto commentAddPost(BookCommentDto bookCommentDto) {
        BookComment comment = bookCommentService.create(bookCommentDto);
        BookCommentDto result = map(comment);
        return result;
    }

    @GetMapping("/comments/{id}")
    public BookCommentDto commentView(@PathVariable Long id) {
        BookComment bookComment = bookCommentService.getById(id);
        BookCommentDto bookCommentDto = map(bookComment);
        return bookCommentDto;
    }

    @PutMapping("/comments/{id}")
    public String commentEditPost(@PathVariable Long id, BookCommentDto bookCommentDto) {
        bookCommentDto.setId(id);
        BookComment bookComment = bookCommentService.update(bookCommentDto);
        return "redirect:/comments";
    }

    @DeleteMapping("/comments/{id}")
    public void bookRemove(@PathVariable Long id) {
        bookCommentService.removeById(id);
    }

    public BookCommentDto map(BookComment bookComment) {
        BookCommentDto bookCommentDto = new BookCommentDto(bookComment.getId(), bookComment.getText(), bookComment.getBook().getId());
        return bookCommentDto;
    }
}
