package ru.otus.shurupov.spring.batch;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableMongock
public class SpringBatchApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBatchApp.class);
        context.close();
    }
}
