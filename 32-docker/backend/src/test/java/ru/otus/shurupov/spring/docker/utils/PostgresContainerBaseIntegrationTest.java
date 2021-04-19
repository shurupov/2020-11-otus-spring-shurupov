package ru.otus.shurupov.spring.docker.utils;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.otus.shurupov.spring.docker.repository.GenreRepository;
import ru.otus.shurupov.spring.docker.service.GenreService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {PostgresContainerBaseIntegrationTest.Initializer.class})
public class PostgresContainerBaseIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:13.2")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    /*@TestConfiguration
    public static class TestContextConfiguration {
        @Bean
        public GenreService genreService(GenreRepository genreRepository) {
            return new GenreServiceImpl(genreRepository);
        }
    }*/

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreService genreService;

    @Test
    public void test1() {
        assertTrue(true);
    }

    @Test
    //@Ignore
    @DisplayName("returns count")
    public void count1() {
        long result = genreRepository.count();
        assertEquals(3L, result);
    }

    @Test
    //@Ignore
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("returns count")
    public void count() {
        long result = genreService.count();
        assertEquals(3L, result);
    }
}
