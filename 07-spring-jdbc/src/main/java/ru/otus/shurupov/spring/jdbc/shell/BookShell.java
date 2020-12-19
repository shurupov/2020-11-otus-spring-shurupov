package ru.otus.shurupov.spring.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.jdbc.domain.Book;
import ru.otus.shurupov.spring.jdbc.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookService bookService;

    @ShellMethod(value = "Get books count", key = {"bc", "book-count"})
    public void booksCount() {
        System.out.println("Books count in library: " + bookService.count());
    }

    @ShellMethod(value = "Get book list", key = {"bl", "book-list"})
    public void bookList() {
        List<Book> books = bookService.getAll();
        System.out.println("Books list");
        int longestNameLength = books.stream().map(b -> b.getName().length()).max(Integer::compare).get();
        System.out.printf("|  id | %-" + longestNameLength + "s |\n", "Name");
        for (Book book : books) {
            System.out.printf("| %3d | %-" + longestNameLength + "s |\n", book.getId(), book.getName());
        }
    }

    @ShellMethod(value = "Add book", key = {"ba", "book-add"})
    public void addBook(@ShellOption String name) {
        bookService.insert(name);
        System.out.println("Book successfully added to the library");
    }

    @ShellMethod(value = "Get book", key = {"bg", "book-get"})
    public void getById(@ShellOption Long id) {
        System.out.println(bookService.getById(id));
    }

}
