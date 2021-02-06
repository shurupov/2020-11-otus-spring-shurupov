package ru.otus.shurupov.spring.springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.shurupov.spring.springmvc.domain.Book;
import ru.otus.shurupov.spring.springmvc.domain.Genre;
import ru.otus.shurupov.spring.springmvc.domain.dto.BookDtoForList;
import ru.otus.shurupov.spring.springmvc.domain.dto.BookRequest;
import ru.otus.shurupov.spring.springmvc.domain.dto.BreadCrumb;
import ru.otus.shurupov.spring.springmvc.service.AuthorService;
import ru.otus.shurupov.spring.springmvc.service.BookService;
import ru.otus.shurupov.spring.springmvc.service.GenreService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/books")
    public String bookList(Model model) {
        List<Book> books = bookService.getAll();
        List<BookDtoForList> bookDtoForLists = books.stream()
                .map(this::map).collect(Collectors.toList());
        model.addAttribute("books", bookDtoForLists);
        model.addAttribute("breadcrumbs", List.of(new BreadCrumb("/books", "Books")));
        return "books/list";
    }

    @PostMapping("/books/add")
    public String bookAddPost(BookRequest bookRequest) {
        bookService.create(bookRequest);
        return "redirect:/books";
    }

    @GetMapping("/books/add")
    public String bookView(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("selectedGenreIds", Collections.emptyList());
        model.addAttribute("breadcrumbs",
                List.of(
                    new BreadCrumb("/books", "Books"),
                    new BreadCrumb("/books/add", "Add Book")
                )
        );
        return "books/add";
    }

    @GetMapping("/books/{id}")
    public String bookView(@PathVariable Long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("selectedGenreIds",
                book.getGenres()
                        .stream()
                        .map(Genre::getId)
                        .collect(Collectors.toList())
        );
        model.addAttribute("breadcrumbs",
                List.of(
                        new BreadCrumb("/books", "Books"),
                        new BreadCrumb("/books", book.getName())
                )
        );
        return "books/edit";
    }

    @PostMapping("/books/{id}")
    public String bookEditPost(@PathVariable Long id, BookRequest bookRequest) {
        bookService.update(id, bookRequest);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/remove")
    public String bookRemove(@PathVariable Long id) {
        bookService.removeById(id);
        return "redirect:/books";
    }

    private BookDtoForList map(Book book) {
        return BookDtoForList.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName())
                .genres(book.getGenres().stream().map(Genre::getName).collect(Collectors.joining(", ")))
                .build();
    }
}
