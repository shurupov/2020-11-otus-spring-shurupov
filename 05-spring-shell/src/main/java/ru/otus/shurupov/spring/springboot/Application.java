package ru.otus.shurupov.spring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.otus.shurupov.spring.springboot.config.QuizProps;
import ru.otus.shurupov.spring.springboot.service.QuizService;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties(QuizProps.class)
@EnableAspectJAutoProxy
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
