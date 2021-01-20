package ru.otus.shurupov.spring.springdatamongodb.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.shurupov.spring.springdatamongodb.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookService bookService;

    @ShellMethod(value = "Get book list", key = {"bl", "book-list"})
    public void bookList() {
        bookService.displayList();
    }
}
