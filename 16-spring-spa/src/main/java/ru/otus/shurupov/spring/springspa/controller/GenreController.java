package ru.otus.shurupov.spring.springspa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.shurupov.spring.springspa.domain.Genre;
import ru.otus.shurupov.spring.springspa.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genres")
    public List<Genre> genreList() {
        List<Genre> genres = genreService.getAll();
        return genres;
    }

    @PostMapping("/genres")
    public Genre genreAddPost(String name) {
        Genre genre = genreService.insert(name);
        return genre;
    }

    @GetMapping("/genres/{id}")
    public Genre genreView(@PathVariable Long id, Model model) {
        Genre genre = genreService.getById(id);
        return genre;
    }

    @PutMapping("/genres/{id}")
    public Genre genreEditPost(@PathVariable Long id, String name) {
        Genre genre = genreService.update(id, name);
        return genre;
    }

    @DeleteMapping("/genres/{id}/remove")
    public void genreRemove(@PathVariable Long id) {
        genreService.removeById(id);
    }
}
