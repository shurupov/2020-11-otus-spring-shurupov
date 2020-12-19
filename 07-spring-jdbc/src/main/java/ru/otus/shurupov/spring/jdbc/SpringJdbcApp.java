package ru.otus.shurupov.spring.jdbc;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.shurupov.spring.jdbc.dao.BookDao;
import ru.otus.shurupov.spring.jdbc.domain.Book;

import java.sql.SQLException;

@SpringBootApplication
public class SpringJdbcApp {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(SpringJdbcApp.class);

        BookDao bookDao = context.getBean(BookDao.class);
        System.out.println("The first book = " + bookDao.getById(1L));
        bookDao.insert(new Book(2L, "Some second book"));
        bookDao.insert(new Book(3L, "Some third book"));
        System.out.println("The second book = " + bookDao.getById(2L));
        System.out.println("The third book = " + bookDao.getById(3L));
        System.out.println("Books count = " + bookDao.count());

        //Console.main(args);
    }
}
