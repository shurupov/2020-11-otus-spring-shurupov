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

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class SpringDataMongodbApp {

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringDataMongodbApp.class);
    }

    @PostConstruct
    public void postConstruct() {
//        Book book = new Book();
//        book.setName("Book 2");
//        bookRepository.save(book);
        System.out.println(bookRepository.findAll());
    }
}
