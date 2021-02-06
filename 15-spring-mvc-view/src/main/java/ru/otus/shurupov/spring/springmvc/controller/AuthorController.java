package ru.otus.shurupov.spring.springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.shurupov.spring.springmvc.domain.Author;
import ru.otus.shurupov.spring.springmvc.domain.dto.BreadCrumb;
import ru.otus.shurupov.spring.springmvc.service.AuthorService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public String authorList(Model model) {
        List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);
        model.addAttribute("breadcrumbs", List.of(new BreadCrumb("/authors", "Authors")));
        return "authors/list";
    }

    @PostMapping("/authors/add")
    public String authorAddPost(Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/authors/add")
    public String bookView(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        model.addAttribute("breadcrumbs",
                List.of(
                        new BreadCrumb("/authors", "Authors"),
                        new BreadCrumb("/authors/add", "Add Author")
                )
        );
        return "authors/add";
    }

    @GetMapping("/authors/{id}")
    public String bookView(@PathVariable Long id, Model model) {
        Author author = authorService.getById(id);
        model.addAttribute("author", author);
        model.addAttribute("breadcrumbs",
                List.of(
                        new BreadCrumb("/authors", "Author"),
                        new BreadCrumb("/authors", author.getFirstName() + " " + author.getLastName())
                )
        );
        return "authors/edit";
    }

    @PostMapping("/authors/{id}")
    public String bookEditPost(@PathVariable Long id, Author author) {
        author.setId(id);
        authorService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/authors/{id}/remove")
    public String bookRemove(@PathVariable Long id) {
        authorService.removeById(id);
        return "redirect:/authors";
    }
}
