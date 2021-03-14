package ru.otus.shurupov.spring.batch.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.shurupov.spring.batch.domain.mongo.MongoBook;
import ru.otus.shurupov.spring.batch.domain.postgres.PostgresBook;
import ru.otus.shurupov.spring.batch.repository.BookMongoRepository;
import ru.otus.shurupov.spring.batch.repository.BookPostgresRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.shurupov.spring.batch.config.job.JobConfig.IMPORT_FROM_MONGO_TO_POSTGRES;

@SpringBootTest
@SpringBatchTest
class JobTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;
    @Autowired
    private BookMongoRepository bookMongoRepository;
    @Autowired
    private BookPostgresRepository bookPostgresRepository;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    void testJob() throws Exception {

        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo(IMPORT_FROM_MONGO_TO_POSTGRES);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParameters());

        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        List<MongoBook> inputBooks = bookMongoRepository.findAll();
        List<PostgresBook> outputBooks = bookPostgresRepository.findAll();

        assertThat(inputBooks.size()).isEqualTo(outputBooks.size());

        for (int i = 0; i < inputBooks.size(); i++) {
            MongoBook inputBook = inputBooks.get(i);
            PostgresBook outputBook = outputBooks.get(i);
            assertAll(
                    () -> assertThat(outputBook.getName())
                            .isEqualTo(inputBook.getName()),
                    () -> assertAll(
                            () -> assertThat(outputBook.getAuthor().getFirstName())
                                    .isEqualTo(inputBook.getAuthor().getFirstName()),
                            () -> assertThat(outputBook.getAuthor().getLastName())
                                    .isEqualTo(inputBook.getAuthor().getLastName())
                    ),
                    () -> assertAll(
                            () -> assertThat(outputBook.getGenres().size())
                                    .isEqualTo(inputBook.getGenres().size()),
                            () -> assertTrue(
                                    outputBook.getGenres().stream().allMatch(
                                            g -> inputBook.getGenres().contains(g.getName())
                                    )
                            )
                    ),
                    () -> assertAll(
                            () -> assertThat(outputBook.getComments().size())
                                    .isEqualTo(inputBook.getComments().size()),
                            () -> assertTrue(
                                    outputBook.getComments().stream().allMatch(
                                            c -> inputBook.getComments().contains(c.getText())
                                    )
                            )
                    )
            );
        }
    }
}