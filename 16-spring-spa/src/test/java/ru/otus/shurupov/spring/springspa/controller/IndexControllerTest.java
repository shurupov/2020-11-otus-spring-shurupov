package ru.otus.shurupov.spring.springspa.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.shurupov.spring.springspa.service.AuthorService;
import ru.otus.shurupov.spring.springspa.service.BookCommentService;
import ru.otus.shurupov.spring.springspa.service.BookService;
import ru.otus.shurupov.spring.springspa.service.GenreService;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.matchesRegex;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndexController.class)
@DisplayName("IndexController ")
class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private BookCommentService bookCommentService;
    @MockBean
    private GenreService genreService;

    @Test
    @DisplayName("returns index page with correct counts")
    void index() throws Exception {
        when(bookService.count()).thenReturn(10L);
        when(authorService.count()).thenReturn(20L);
        when(bookCommentService.count()).thenReturn(30L);
        when(genreService.count()).thenReturn(40L);
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<span class=\"badge bg-primary rounded-pill text-light\">10</span>")))
                .andExpect(content().string(containsString("<span class=\"badge bg-primary rounded-pill text-light\">20</span>")))
                .andExpect(content().string(containsString("<span class=\"badge bg-primary rounded-pill text-light\">30</span>")))
                .andExpect(content().string(containsString("<span class=\"badge bg-primary rounded-pill text-light\">40</span>")))
        ;
    }
}