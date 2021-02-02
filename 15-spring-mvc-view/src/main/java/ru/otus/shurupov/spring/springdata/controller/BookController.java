package ru.otus.shurupov.spring.springdata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.shurupov.spring.springdata.domain.Book;
import ru.otus.shurupov.spring.springdata.domain.Genre;
import ru.otus.shurupov.spring.springdata.domain.dto.BookDto;
import ru.otus.shurupov.spring.springdata.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public String bookList(Model model) {
        List<Book> books = bookService.getAll();
        List<BookDto> bookDtos = books.stream()
                .map(this::map).collect(Collectors.toList());
        model.addAttribute("books", bookDtos);
        return "books/list";
    }

    @GetMapping("/books/{id}")
    public String bookView(@PathVariable Long id, Model model) {
        Book book = bookService.getById(id).get();
        BookDto bookDto = map(book);
        model.addAttribute("book", bookDto);
        return "books/edit";
    }

    @PostMapping("/books/{id}")
    public String bookEditPost(@PathVariable Long id, BookDto bookDto) {
        bookService.updateName(id, bookDto.getName());
        return "redirect:/books/" + id;
    }

    @GetMapping("/books/{id}/remove")
    public String bookRemove(@PathVariable Long id) {
        bookService.removeById(id);
        return "redirect:/books";
    }

    private BookDto map(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName())
                .genres(book.getGenres().stream().map(Genre::getName).collect(Collectors.joining(", ")))
                .build();
    }
}
