package ru.otus.shurupov.spring.springdatamongodb;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.shurupov.spring.springdatamongodb.domain.Book;
import ru.otus.shurupov.spring.springdatamongodb.repository.BookRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class SpringDataMongodbApp {

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringDataMongodbApp.class);
    }
}
