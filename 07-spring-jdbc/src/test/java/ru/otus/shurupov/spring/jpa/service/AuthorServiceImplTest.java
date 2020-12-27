package ru.otus.shurupov.spring.jpa.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.shurupov.spring.jpa.dao.AuthorDao;
import ru.otus.shurupov.spring.jpa.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("AuthorServiceImpl")
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorDao authorDao;

    private AuthorService authorService;

    @BeforeEach
    private void init() {
        authorService = new AuthorServiceImpl(authorDao);
    }

    @Test
    @DisplayName("returns correct authors count in library")
    void shouldCount() {
        when(authorDao.count()).thenReturn(5);
        int actual = authorService.count();
        assertAll(
                () -> assertThat(actual).isEqualTo(5),
                () -> verify(authorDao, times(1)).count()
        );
    }

    @Test
    @DisplayName("returns correct author by id")
    void shouldGetById() {
        Author expected = new Author(1L, "Evgeny", "Shurupov");
        when(authorDao.getById(eq(1L))).thenReturn(expected);
        Author actual = authorService.getById(1L);
        assertAll(
                () -> assertThat(actual).isEqualTo(expected),
                () -> verify(authorDao, times(1)).getById(eq(1L))
        );
    }
}