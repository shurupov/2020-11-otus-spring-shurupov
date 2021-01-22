package ru.otus.shurupov.spring.springdatamongodb.repository;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("can't remove author that has books")
    void shouldntRemoveAuthor() {
        assertThrows(
                RuntimeException.class,
                () -> authorRepository.deleteById("1"),
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