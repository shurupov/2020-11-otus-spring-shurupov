package ru.otus.shurupov.spring.springshell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.otus.shurupov.spring.springshell.config.QuizProps;

@SpringBootApplication
@EnableConfigurationProperties(QuizProps.class)
@EnableAspectJAutoProxy
public class SpringShellApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringShellApplication.class, args);
	}
}
