package ru.otus.shurupov.spring.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.jdbc.domain.Author;
import ru.otus.shurupov.spring.jdbc.domain.Genre;
import ru.otus.shurupov.spring.jdbc.service.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorService authorService;

    @ShellMethod(value = "Get authors count", key = {"ac", "author-count"})
    public void authorsCount() {
        System.out.println("Authors count in library: " + authorService.count());
    }

    @ShellMethod(value = "Get author list", key = {"al", "author-list"})
    public void authorList() {
        List<Author> authors = authorService.getAll();
        System.out.println("Authors list");
        int longestFirstNameLength = authors.stream().map(b -> b.getFirstName().length()).max(Integer::compare).get();
        int longestLastNameLength = authors.stream().map(b -> b.getLastName().length()).max(Integer::compare).get();
        System.out.printf("|  id | %-" + longestFirstNameLength + "s |\n", "Name");
        for (Author author : authors) {
            System.out.printf("| %3d | %-" + longestFirstNameLength + "s | %-" + longestLastNameLength + "s |\n",
                    author.getId(), author.getFirstName(), author.getLastName());
        }
    }

    @ShellMethod(value = "Add author", key = {"aa", "author-add"})
    public void addAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        authorService.insert(firstName, lastName);
        System.out.println("Author successfully added to the library");
    }

    @ShellMethod(value = "Get author", key = {"ag", "author-get"})
    public void getById(@ShellOption Long id) {
        System.out.println(authorService.getById(id));
    }
}
