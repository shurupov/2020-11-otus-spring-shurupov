package ru.otus.shurupov.spring.jdbc.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.shurupov.spring.jdbc.domain.Genre;
import ru.otus.shurupov.spring.jdbc.service.GenreService;

import java.util.List;

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
        List<Genre> genres = genreService.getAll();
        System.out.println("Genres list");
        int longestNameLength = genres.stream().map(b -> b.getName().length()).max(Integer::compare).get();
        System.out.printf("|  id | %-" + longestNameLength + "s |\n", "Name");
        for (Genre genre : genres) {
            System.out.printf("| %3d | %-" + longestNameLength + "s |\n", genre.getId(), genre.getName());
        }
    }

    @ShellMethod(value = "Add genre", key = {"ga", "genre-add"})
    public void addGenre(@ShellOption String name) {
        genreService.insert(name);
        System.out.println("Genre successfully added to the library");
    }

    @ShellMethod(value = "Get genre", key = {"gg", "genre-get"})
    public void getById(@ShellOption Long id) {
        System.out.println(genreService.getById(id));
    }
}
