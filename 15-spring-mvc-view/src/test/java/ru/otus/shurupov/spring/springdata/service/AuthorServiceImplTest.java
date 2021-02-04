package ru.otus.shurupov.spring.springdata.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.shurupov.spring.springdata.repository.AuthorRepository;
import ru.otus.shurupov.spring.springdata.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("AuthorServiceImpl")
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private TableRenderer tableRenderer;

    private AuthorService authorService;

    @BeforeEach
    private void init() {
        authorService = new AuthorServiceImpl(authorRepository);
    }

    @Test
    @DisplayName("returns correct authors count in library")
    void shouldCount() {
        when(authorRepository.count()).thenReturn(5L);
        long actual = authorService.count();
        assertAll(
                () -> assertThat(actual).isEqualTo(5),
                () -> verify(authorRepository, times(1)).count()
        );
    }

    @Test
    @DisplayName("returns correct author by id")
    void shouldGetById() {
        Author expected = new Author(1L, "Evgeny", "Shurupov");
        when(authorRepository.findById(eq(1L))).thenReturn(Optional.of(expected));
        Author actual = authorService.getById(1L);
        assertAll(
                () -> assertThat(actual).isEqualTo(expected),
                () -> verify(authorRepository, times(1)).findById(eq(1L))
        );
    }
}