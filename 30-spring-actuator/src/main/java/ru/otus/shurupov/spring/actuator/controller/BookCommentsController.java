package ru.otus.shurupov.spring.actuator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.shurupov.spring.actuator.domain.BookComment;
import ru.otus.shurupov.spring.actuator.domain.dto.BookCommentDto;
import ru.otus.shurupov.spring.actuator.service.BookCommentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookCommentsController {

    private final BookCommentService bookCommentService;

    @GetMapping("/api/books/{bookId}/comments")
    public List<BookCommentDto> bookList(@PathVariable Long bookId) {
        List<BookComment> comments = bookCommentService.getBookComments(bookId);
        return comments.stream().map(this::map).collect(Collectors.toList());
    }

    @PostMapping("/api/books/{bookId}/comments")
    public void commentAddPost(@PathVariable Long bookId, @RequestBody BookCommentDto bookCommentDto) {
        bookCommentService.create(bookId, bookCommentDto);
    }

    @GetMapping("/api/books/{bookId}/comments/{id}")
    public BookCommentDto commentView(@PathVariable Long bookId, @PathVariable Long id) {
        BookComment bookComment = bookCommentService.getById(id);
        BookCommentDto bookCommentDto = map(bookComment);
        return bookCommentDto;
    }

    @PutMapping("/api/books/{bookId}/comments/{id}")
    public void commentEditPost(@PathVariable Long bookId, @PathVariable Long id, @RequestBody BookCommentDto bookCommentDto) {
        bookCommentDto.setId(id);
        bookCommentService.update(bookCommentDto);
    }

    @DeleteMapping("/api/books/{bookId}/comments/{id}")
    public void bookRemove(@PathVariable Long id) {
        bookCommentService.removeById(id);
    }

    public BookCommentDto map(BookComment bookComment) {
        return new BookCommentDto(bookComment.getId(), bookComment.getText());
    }
}
