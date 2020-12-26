package ru.otus.shurupov.spring.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.jdbc.domain.Book;
import ru.otus.shurupov.spring.jdbc.service.BookService;
import ru.otus.shurupov.spring.jdbc.service.TableRenderer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookService bookService;
    private final TableRenderer tableRenderer;

    @ShellMethod(value = "Get books count", key = {"bc", "book-count"})
    public void booksCount() {
        System.out.println("Books count in library: " + bookService.count());
    }

    @ShellMethod(value = "Get book list", key = {"bl", "book-list"})
    public void bookList() {
        List<Book> books = bookService.getAll();
        System.out.println(
                tableRenderer.render(
                    "Library book list",
                    Arrays.asList("id", "Name", "Author", "Genre"),
                    (book) -> Arrays.asList(book.getId().toString(), book.getName(), book.getAuthorCaption(), book.getGenreCaption()),
                    books
                )
        );
    }

    @ShellMethod(value = "Add book", key = {"ba", "book-add"})
    public void addBook(@ShellOption String name, @ShellOption Long authorId, @ShellOption Long genreId) {
        bookService.insert(name, authorId, genreId);
        System.out.println("Book successfully added to the library");
    }

    @ShellMethod(value = "Get book", key = {"bg", "book-get"})
    public void getById(@ShellOption Long id) {
        Optional<Book> optionalBook = bookService.getById(id);
        if (optionalBook.isPresent()) {
            System.out.println(
                    tableRenderer.singleRowRender(
                            "Book",
                            Arrays.asList("id", "Name", "Author", "Genre"),
                            (book) -> Arrays.asList(book.getId().toString(), book.getName(), book.getAuthorCaption(), book.getGenreCaption()),
                            optionalBook.get()
                    )
            );
        } else {
            System.out.println("Book with id " + id + " not found");
        }
    }

    @ShellMethod(value = "Remove book", key = {"br", "book-remove"})
    public void removeById(@ShellOption Long id) {
        bookService.removeById(id);
        System.out.println("Book successfully removed");
    }

    @ShellMethod(value = "Update book name", key = {"bnu", "book-name-update"})
    public void updateById(@ShellOption Long id, @ShellOption String name) {
        bookService.updateName(id, name);
        System.out.println("Book successfully updated");
    }

}
