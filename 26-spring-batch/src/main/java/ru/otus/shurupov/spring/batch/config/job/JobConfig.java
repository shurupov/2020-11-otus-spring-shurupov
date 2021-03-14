package ru.otus.shurupov.spring.batch.config.job;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class JobConfig {

    public static final String IMPORT_FROM_MONGO_TO_POSTGRES = "importFromMongoToPostgres";

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
