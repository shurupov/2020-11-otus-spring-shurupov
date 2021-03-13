package ru.otus.shurupov.spring.authorization.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.shurupov.spring.authorization.domain.Author;
import ru.otus.shurupov.spring.authorization.service.AuthorService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
@DisplayName("AuthorController ")
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @TestConfiguration
    public static class UserDetailsServiceConfiguration {
        @Bean
        public UserDetailsService userDetailsService() {
            return username -> new User(username, "12345", List.of());
        }
    }

    @Test
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("returns author list")
    void authorList() throws Exception {
        when(authorService.getAll()).thenReturn(List.of());
        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("fails to respond author for USER")
    void getAuthorFail() throws Exception {
        when(authorService.getById(anyLong())).thenReturn(new Author(1L, "Alexander", "Pushkin"));
        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("successfully responds author for ADMIN")
    void getAuthorSuccess() throws Exception {
        when(authorService.getById(anyLong())).thenReturn(new Author(1L, "Alexander", "Pushkin"));
        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isOk());
    }
}