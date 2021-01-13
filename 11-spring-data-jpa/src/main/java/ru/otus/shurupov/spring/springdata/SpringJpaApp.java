package ru.otus.shurupov.spring.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringJpaApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringJpaApp.class);
        context.close();
    }
}
