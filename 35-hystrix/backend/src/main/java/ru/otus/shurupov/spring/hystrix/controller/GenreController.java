package ru.otus.shurupov.spring.hystrix.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.shurupov.spring.hystrix.domain.Genre;
import ru.otus.shurupov.spring.hystrix.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/api/genres")
    public List<Genre> genreList() {
        List<Genre> genres = genreService.getAll();
        return genres;
    }

    @PostMapping("/api/genres")
    public Genre genreAddPost(@RequestBody Genre genre) {
        genre = genreService.insert(genre);
        return genre;
    }

    @GetMapping("/api/genres/{id}")
    public Genre genreView(@PathVariable Long id) {
        Genre genre = genreService.getById(id);
        return genre;
    }

    @PutMapping("/api/genres/{id}")
    public Genre genreEditPost(@PathVariable Long id, @RequestBody Genre genre) {
        genre = genreService.update(id, genre);
        return genre;
    }

    @DeleteMapping("/api/genres/{id}")
    public void genreRemove(@PathVariable Long id) {
        genreService.removeById(id);
    }
}
