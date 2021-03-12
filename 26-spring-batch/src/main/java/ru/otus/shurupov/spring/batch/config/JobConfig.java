package ru.otus.shurupov.spring.batch.config;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.shurupov.spring.batch.domain.mongo.Book;
import ru.otus.shurupov.spring.batch.domain.postgres.Author;
import ru.otus.shurupov.spring.batch.domain.postgres.PostgresBook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;

@Configuration
public class JobConfig {

    private static final int CHUNK_SIZE = 5;

    public static final String IMPORT_FROM_MONGO_TO_POSTGRES = "importFromMongoToPostgres";

    @Autowired
    private MongoTemplate mongoTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

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
            MongoItemReader<Book> mongoBookReader,
            JpaItemWriter<PostgresBook> postgresBookWriter,
            ItemProcessor<Book, PostgresBook> mongoToPostgresProcessor
            ) {
        return stepBuilderFactory.get("mongoToPostgresStep")
                .<Book, PostgresBook>chunk(CHUNK_SIZE)
                .reader(mongoBookReader)
                .processor(mongoToPostgresProcessor)
                .writer(postgresBookWriter)
                .build();
    }

    @Bean
    public MongoItemReader<Book> mongoBookReader() {
        return new MongoItemReaderBuilder<Book>()
                .name("mongoBookReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(Book.class)
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
    public ItemProcessor<Book, PostgresBook> mongoToPostgresProcessor() {
        return (Book inputBook) -> {
            PostgresBook outputBook = new PostgresBook();
            outputBook.setName(inputBook.getName());
            outputBook.setAuthor(new Author(2L, "Agatha", "Christie"));
            return outputBook;
        };
    }
}
