package ru.otus.shurupov.spring.docker.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import ru.otus.shurupov.spring.docker.domain.Genre;
import ru.otus.shurupov.spring.docker.repository.GenreRepository;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GenreServiceImpl")
@SpringBootTest
class GenreServiceImplTest {

    @TestConfiguration
    public static class TestContextConfiguration {
        @Bean
        public GenreService genreService(GenreRepository genreRepository) {
            return new GenreServiceImpl(genreRepository);
        }
    }

    @Autowired
    private GenreService genreService;

    @Test
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("returns count")
    void count() {
        long result = genreService.count();
        assertEquals(3L, result);
    }

    @Test
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("fails to return genre")
    void getByIdFail() {
        assertThrows(AccessDeniedException.class, () -> genreService.getById(5L));
    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("returns genre to admin")
    void getByIdSuccess() {
        Genre result = genreService.getById(2L);
        assertEquals(2L, result.getId().longValue());
        assertEquals("Fairy Tale", result.getName());
    }
}