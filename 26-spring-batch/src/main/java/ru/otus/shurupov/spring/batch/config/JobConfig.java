package ru.otus.shurupov.spring.batch.config;

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

    private static final int CHUNK_SIZE = 5;

    public static final String IMPORT_FROM_MONGO_TO_POSTGRES = "importFromMongoToPostgres";

    private final MongoTemplate mongoTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    private final StepBuilderFactory stepBuilderFactory;

    private final JobBuilderFactory jobBuilderFactory;

    private final BookProcessService bookProcessService;

    @Bean
    public Job fromMongoToPostgres(Step mongoToPostgresStep) {
        return jobBuilderFactory.get(IMPORT_FROM_MONGO_TO_POSTGRES)
                .incrementer(new RunIdIncrementer())
                .flow(mongoToPostgresStep)
                .end()
                .build();
    }

    @Bean
    public Step mongoToPostgresStep(
            MongoItemReader<MongoBook> mongoBookReader,
            JpaItemWriter<PostgresBook> postgresBookWriter,
            ItemProcessor<MongoBook, PostgresBook> mongoToPostgresProcessor
            ) {
        return stepBuilderFactory.get("mongoToPostgresStep")
                .<MongoBook, PostgresBook>chunk(CHUNK_SIZE)
                .reader(mongoBookReader)
                .processor(mongoToPostgresProcessor)
                .writer(postgresBookWriter)
                .build();
    }

    @Bean
    public MongoItemReader<MongoBook> mongoBookReader() {
        return new MongoItemReaderBuilder<MongoBook>()
                .name("mongoBookReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(MongoBook.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public JpaItemWriter<PostgresBook> postgresBookWriter() {
        return new JpaItemWriterBuilder<PostgresBook>()
                .entityManagerFactory(entityManager.getEntityManagerFactory())
                .usePersist(true)
                .build();
    }

    @Bean
    public ItemProcessor<MongoBook, PostgresBook> mongoToPostgresProcessor() {
        return bookProcessService::process;
    }
}
