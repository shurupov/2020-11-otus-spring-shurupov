package ru.otus.shurupov.spring.batch.config.job;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.shurupov.spring.batch.domain.mongo.MongoBook;
import ru.otus.shurupov.spring.batch.domain.postgres.PostgresBook;
import ru.otus.shurupov.spring.batch.service.BookProcessService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;

@Configuration
@AllArgsConstructor
public class JobConfig {

    public static final String IMPORT_FROM_MONGO_TO_POSTGRES = "importFromMongoToPostgres";

    @PersistenceContext
    private EntityManager entityManager;

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job fromMongoToPostgres(Step saveBookGenreAuthorStep) {
        return jobBuilderFactory.get(IMPORT_FROM_MONGO_TO_POSTGRES)
                .incrementer(new RunIdIncrementer())
                .flow(saveBookGenreAuthorStep)
                .end()
                .build();
    }
}
