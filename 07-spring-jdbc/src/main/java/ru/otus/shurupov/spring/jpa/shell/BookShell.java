package ru.otus.shurupov.spring.jpa.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.jpa.domain.dto.BookDto;
import ru.otus.shurupov.spring.jpa.service.BookService;
import ru.otus.shurupov.spring.jpa.service.TableRenderer;

import java.util.Arrays;
import java.util.List;

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
        List<BookDto> books = bookService.getAll();
        System.out.println(
                tableRenderer.render(
                    "Library book list",
                    Arrays.asList("id", "Name", "Author", "Genre"),
                    (book) -> Arrays.asList(book.getId().toString(), book.getName(), book.getAuthor(), book.getGenre()),
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
        System.out.println(
                tableRenderer.singleRowRender(
                        "Book",
                        Arrays.asList("id", "Name", "Author", "Genre"),
                        (book) -> Arrays.asList(book.getId().toString(), book.getName(), book.getAuthor(), book.getGenre()),
                        bookService.getById(id)
                )
        );
    }

    @ShellMethod(value = "Remove book", key = {"br", "book-remove"})
    public void removeById(@ShellOption Long id) {
        bookService.removeById(id);
        System.out.println("Book successfully removed");
    }

    @ShellMethod(value = "Update book", key = {"bu", "book-update"})
    public void updateById(@ShellOption Long id, @ShellOption String name, @ShellOption Long authorId, @ShellOption Long genreId) {
        bookService.update(id, name, authorId, genreId);
        System.out.println("Book successfully updated");
    }

}
