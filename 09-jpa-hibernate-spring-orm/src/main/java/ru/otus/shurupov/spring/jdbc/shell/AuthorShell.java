package ru.otus.shurupov.spring.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.jdbc.domain.Author;
import ru.otus.shurupov.spring.jdbc.service.AuthorService;
import ru.otus.shurupov.spring.jdbc.service.TableRenderer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorService authorService;
    private final TableRenderer tableRenderer;

    @ShellMethod(value = "Get authors count", key = {"ac", "author-count"})
    public void authorsCount() {
        System.out.println("Authors count in library: " + authorService.count());
    }

    @ShellMethod(value = "Get author list", key = {"al", "author-list"})
    public void authorList() {
        List<Author> authors = authorService.getAll();
        System.out.println(
                tableRenderer.render(
                        "Authors list",
                        Arrays.asList("id", "First Name", "Last Name"),
                        (author) -> Arrays.asList(author.getId().toString(), author.getFirstName(), author.getLastName()),
                        authors
                )
        );
    }

    @ShellMethod(value = "Add author", key = {"aa", "author-add"})
    public void addAuthor(@ShellOption String firstName, @ShellOption String lastName) {
        authorService.insert(firstName, lastName);
        System.out.println("Author successfully added to the library");
    }

    @ShellMethod(value = "Get author", key = {"ag", "author-get"})
    public void getById(@ShellOption Long id) {
        Optional<Author> optionalAuthor = authorService.getById(id);
        if (optionalAuthor.isPresent()) {
            System.out.println(
                    tableRenderer.singleRowRender(
                            "Author",
                            Arrays.asList("id", "First Name", "Last Name"),
                            (author) -> Arrays.asList(author.getId().toString(), author.getFirstName(), author.getLastName()),
                            optionalAuthor.get()
                    )
            );
        } else {
            System.out.println("Author with id " + id + " not found");
        }
    }
}
