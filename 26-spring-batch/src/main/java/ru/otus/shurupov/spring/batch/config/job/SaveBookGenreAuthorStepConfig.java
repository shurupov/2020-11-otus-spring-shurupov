package ru.otus.shurupov.spring.batch.config.job;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.shurupov.spring.batch.domain.mongo.MongoBook;
import ru.otus.shurupov.spring.batch.domain.postgres.BookComment;
import ru.otus.shurupov.spring.batch.domain.postgres.PostgresBook;
import ru.otus.shurupov.spring.batch.repository.CommentRepository;
import ru.otus.shurupov.spring.batch.service.BookProcessService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

@Configuration
@AllArgsConstructor
public class SaveBookGenreAuthorStepConfig {

    private static final int CHUNK_SIZE = 5;

    private final MongoTemplate mongoTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    private final StepBuilderFactory stepBuilderFactory;
    private final BookProcessService bookProcessService;

    private final CommentRepository commentRepository;

    @Bean
    public Step saveBookGenreAuthorStep(
            MongoItemReader<MongoBook> mongoBookReader,
            JpaItemWriter<PostgresBook> postgresBookWriter,
            ItemProcessor<MongoBook, PostgresBook> mongoToPostgresBookGenreAuthorProcessor,
            ItemWriteListener<PostgresBook> afterBookWriteCommentsWriter
    ) {
        return stepBuilderFactory.get("saveBookGenreAuthorStep")
                .<MongoBook, PostgresBook>chunk(CHUNK_SIZE)
                .reader(mongoBookReader)
                .processor(mongoToPostgresBookGenreAuthorProcessor)
                .writer(postgresBookWriter)
                .listener(afterBookWriteCommentsWriter)
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
    public ItemProcessor<MongoBook, PostgresBook> mongoToPostgresBookGenreAuthorProcessor() {
        return bookProcessService::process;
    }

    @Bean
    public ItemWriteListener<PostgresBook> afterBookWriteCommentsWriter() {
        return new ItemListenerSupport<>() {
            @Override
            public void afterWrite(List<? extends PostgresBook> books) {
                for (PostgresBook book : books) {
                    for (BookComment comment : book.getComments()) {
                        commentRepository.save(comment);
                    }
                }
            }
        };
    }
}
