package ru.otus.shurupov.spring.springshell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.otus.shurupov.spring.springshell.config.QuizProps;
import ru.otus.shurupov.spring.springshell.service.QuizService;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties(QuizProps.class)
@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		QuizService quizService = context.getBean(QuizService.class);
		quizService.quiz();
	}

}
