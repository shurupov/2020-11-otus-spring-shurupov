package ru.otus.shurupov.spring.springdata.shell;

import lombok.RequiredArgsConstructor;
import ru.otus.shurupov.spring.springdata.service.BookService;

import java.util.List;

//@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    /*private final BookService bookService;

    @ShellMethod(value = "Get books count", key = {"bc", "book-count"})
    public void booksCount() {
        System.out.println("Books count in library: " + bookService.count());
    }

    @ShellMethod(value = "Get book list", key = {"bl", "book-list"})
    public void bookList() {
        bookService.displayList();
    }

    @ShellMethod(value = "Filter book list by author", key = {"bfa", "book-filter-by-author"})
    public void bookListByAuthor(@ShellOption String filter) {
        bookService.displayByAuthorFilteredList(filter);
    }

    @ShellMethod(value = "Filter book list by name", key = {"bfn", "book-filter-by-name"})
    public void bookListByName(@ShellOption String filter) {
        bookService.displayByNameFilteredList(filter);
    }

    @ShellMethod(value = "Add book", key = {"ba", "book-add"})
    public void addBook(@ShellOption String name, @ShellOption Long authorId, @ShellOption Long genreId) {
        bookService.insert(name, authorId, genreId);
        System.out.println("Book successfully added to the library");
    }

    @ShellMethod(value = "Get book", key = {"bg", "book-get"})
    public void getById(@ShellOption Long id) {
        bookService.displayById(id);
    }

    @ShellMethod(value = "Remove book", key = {"br", "book-remove"})
    public void removeById(@ShellOption Long id) {
        bookService.removeById(id);
        System.out.println("Book successfully removed");
    }

    @ShellMethod(value = "Update book name", key = {"bnu", "book-name-update"})
    public void updateBookName(@ShellOption Long id, @ShellOption String name) {
        bookService.updateName(id, name);
        System.out.println("Book successfully updated");
    }

    @ShellMethod(value = "Update book genres", key = {"bgu", "book-genres-update"})
    public void updateBookGenres(@ShellOption Long id, @ShellOption List<Long> genreIds) {
        bookService.setGenres(id, genreIds);
        System.out.println("Book successfully updated");
    }*/
}
