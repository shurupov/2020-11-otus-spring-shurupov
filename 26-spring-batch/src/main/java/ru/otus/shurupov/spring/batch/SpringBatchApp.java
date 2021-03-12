package ru.otus.shurupov.spring.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBatchApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBatchApp.class);
        context.close();
    }
}
