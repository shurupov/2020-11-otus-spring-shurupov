package ru.otus.shurupov.spring.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.jdbc.domain.Genre;
import ru.otus.shurupov.spring.jdbc.service.GenreService;
import ru.otus.shurupov.spring.jdbc.service.TableRenderer;

import java.util.Arrays;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreService genreService;
    private final TableRenderer tableRenderer;

    @ShellMethod(value = "Get genres count", key = {"gc", "genre-count"})
    public void genresCount() {
        System.out.println("Genres count in library: " + genreService.count());
    }

    @ShellMethod(value = "Get genre list", key = {"gl", "genre-list"})
    public void genreList() {
        List<Genre> genres = genreService.getAll();
        System.out.println(
                tableRenderer.render(
                        "Genres list",
                        Arrays.asList("id", "Genre name"),
                        (genre) -> Arrays.asList(genre.getId().toString(), genre.getName()),
                        genres
                )
        );
    }

    @ShellMethod(value = "Add genre", key = {"ga", "genre-add"})
    public void addGenre(@ShellOption String name) {
        genreService.insert(name);
        System.out.println("Genre successfully added to the library");
    }

    @ShellMethod(value = "Get genre", key = {"gg", "genre-get"})
    public void getById(@ShellOption Long id) {
        System.out.println(
                tableRenderer.singleRowRender(
                        "Genre",
                        Arrays.asList("id", "Genre name"),
                        (genre) -> Arrays.asList(genre.getId().toString(), genre.getName()),
                        genreService.getById(id)
                )
        );
    }
}
