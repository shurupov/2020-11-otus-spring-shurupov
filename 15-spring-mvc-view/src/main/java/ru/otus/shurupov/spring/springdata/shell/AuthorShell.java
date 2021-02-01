package ru.otus.shurupov.spring.springdata.shell;

import lombok.RequiredArgsConstructor;
import ru.otus.shurupov.spring.springdata.service.AuthorService;

//@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    /*private final AuthorService authorService;

    @ShellMethod(value = "Get authors count", key = {"ac", "author-count"})
    public void authorsCount() {
        System.out.println("Authors count in library: " + authorService.count());
    }

    @ShellMethod(value = "Get author list", key = {"al", "author-list"})
    public void authorList() {
        authorService.displayList();
    }

    @ShellMethod(value = "Add author", key = {"aa", "author-add"})
    public void addAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        authorService.insert(firstName, lastName);
        System.out.println("Author successfully added to the library");
    }

    @ShellMethod(value = "Get author", key = {"ag", "author-get"})
    public void getById(@ShellOption Long id) {
        authorService.displayById(id);
    }*/
}
