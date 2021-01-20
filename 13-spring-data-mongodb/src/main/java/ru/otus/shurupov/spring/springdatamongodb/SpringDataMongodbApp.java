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

//@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class SpringDataMongodbApp {

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringDataMongodbApp.class);
    }

    //@PostConstruct
    public void postConstruct() {
        Book book1 = new Book();
        book1.setName("Book 1");
        book1.setGenres(List.of("Horror", "Comedy"));
        bookRepository.save(book1);
        Book book2 = new Book();
        book2.setGenres(List.of("Drama", "Tragedy"));
        book2.setName("Book 2");
        bookRepository.save(book2);
        Book book3 = new Book();
        book3.setGenres(List.of());
        book3.setName("Book 3");
        bookRepository.save(book3);
        System.out.println(bookRepository.findAll());
    }
}
