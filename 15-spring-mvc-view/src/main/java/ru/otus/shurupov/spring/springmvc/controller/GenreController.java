package ru.otus.shurupov.spring.springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.shurupov.spring.springmvc.domain.Genre;
import ru.otus.shurupov.spring.springmvc.domain.dto.BreadCrumb;
import ru.otus.shurupov.spring.springmvc.service.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genres")
    public String genreList(Model model) {
        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);
        model.addAttribute("breadcrumbs", List.of(new BreadCrumb("/genres", "Genres")));
        return "genres/list";
    }

    @PostMapping("/genres/add")
    public String genreAddPost(String name) {
        genreService.insert(name);
        return "redirect:/genres";
    }

    @GetMapping("/genres/add")
    public String genreView(Model model) {
        model.addAttribute("breadcrumbs",
                List.of(
                        new BreadCrumb("/genres", "Genres"),
                        new BreadCrumb("/genres/add", "Add Genre")
                )
        );
        return "genres/add";
    }

    @GetMapping("/genres/{id}")
    public String genreView(@PathVariable Long id, Model model) {
        Genre genre = genreService.getById(id);
        model.addAttribute("genre", genre);
        model.addAttribute("breadcrumbs",
                List.of(
                        new BreadCrumb("/genres", "Genres"),
                        new BreadCrumb("/genres", genre.getName())
                )
        );
        return "genres/edit";
    }

    @PostMapping("/genres/{id}")
    public String genreEditPost(@PathVariable Long id, String name) {
        genreService.update(id, name);
        return "redirect:/genres";
    }

    @GetMapping("/genres/{id}/remove")
    public String genreRemove(@PathVariable Long id) {
        genreService.removeById(id);
        return "redirect:/genres";
    }
}
