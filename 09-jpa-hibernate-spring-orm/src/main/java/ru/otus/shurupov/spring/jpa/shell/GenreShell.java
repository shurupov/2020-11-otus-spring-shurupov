package ru.otus.shurupov.spring.jpa.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.jpa.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreService genreService;

    @ShellMethod(value = "Get genres count", key = {"gc", "genre-count"})
    public void genresCount() {
        System.out.println("Genres count in library: " + genreService.count());
    }

    @ShellMethod(value = "Get genre list", key = {"gl", "genre-list"})
    public void genreList() {
        genreService.displayList();
    }

    @ShellMethod(value = "Add genre", key = {"ga", "genre-add"})
    public void addGenre(@ShellOption String name) {
        genreService.insert(name);
        System.out.println("Genre successfully added to the library");
    }

    @ShellMethod(value = "Get genre", key = {"gg", "genre-get"})
    public void getById(@ShellOption Long id) {
        genreService.displayById(id);
    }
}
