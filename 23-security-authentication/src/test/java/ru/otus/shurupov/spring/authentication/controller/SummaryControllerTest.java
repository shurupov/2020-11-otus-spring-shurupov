package ru.otus.shurupov.spring.authentication.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.shurupov.spring.authentication.service.AuthorService;
import ru.otus.shurupov.spring.authentication.service.BookCommentService;
import ru.otus.shurupov.spring.authentication.service.BookService;
import ru.otus.shurupov.spring.authentication.service.GenreService;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SummaryController.class)
@DisplayName("SummaryController ")
class SummaryControllerTest {

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

    @TestConfiguration
    public static class UserDetailsServiceConfiguration {
        @Bean
        public UserDetailsService userDetailsService() {
            return new UserDetailsService() {
                @Override
                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                    return new User(username, "12345", List.of());
                }
            };
        }
    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("returns index page with correct counts is user is authenticated")
    void index() throws Exception {
        when(bookService.count()).thenReturn(10L);
        when(authorService.count()).thenReturn(20L);
        when(bookCommentService.count()).thenReturn(30L);
        when(genreService.count()).thenReturn(40L);
        mockMvc.perform(get("/api/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books", is(10)))
                .andExpect(jsonPath("$.authors", is(20)))
                .andExpect(jsonPath("$.comments", is(30)))
                .andExpect(jsonPath("$.genres", is(40)))
        ;
    }

    @Test
    @DisplayName("returns 3xx redirect if user is not authenticated")
    void indexUnauthenticated() throws Exception {
        mockMvc.perform(get("/api/summary"))
                .andExpect(status().is3xxRedirection())
        ;
    }
}