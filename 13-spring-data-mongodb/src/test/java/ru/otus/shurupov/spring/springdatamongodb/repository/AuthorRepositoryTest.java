package ru.otus.shurupov.spring.springdatamongodb.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.shurupov.spring.springdatamongodb.domain.Author;
import ru.otus.shurupov.spring.springdatamongodb.mongolistener.AuthorEventListener;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@DisplayName("AuthorRepository ")
@DataMongoTest
class AuthorRepositoryTest {

    @TestConfiguration
    public static class TestContextConfiguration {
        @Bean
        public AuthorEventListener authorEventListener() {
            return new AuthorEventListener();
        }
    }

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("can't remove author that has books")
    @ParameterizedTest
    @ValueSource(strings = { "1", "2", "3", "4" })
    void shouldntRemoveAuthor(String authorId) {
        assertThrows(
                RuntimeException.class,
                () -> authorRepository.deleteById(authorId),
                "This author has books in the library. Can't be removed."
        );
    }

    @Test
    @DisplayName("can remove author that doesn't have books")
    @DirtiesContext(methodMode = AFTER_METHOD)
    void shouldSuccessfullyRemoveAuthor() {
        authorRepository.deleteById("5");
        List<Author> authors = authorRepository.findAll();
        assertThat(authors)
                .hasSize(4)
                .allMatch(a -> !"5".equals(a.getId()));
    }
}