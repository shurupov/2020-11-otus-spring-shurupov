package ru.otus.shurupov.spring.jdbc;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class SpringJdbcApp {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(SpringJdbcApp.class);

        Console.main(args);
    }
}
