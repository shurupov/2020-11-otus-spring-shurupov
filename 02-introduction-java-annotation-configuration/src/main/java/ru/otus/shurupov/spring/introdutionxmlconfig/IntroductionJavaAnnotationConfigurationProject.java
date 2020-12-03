package ru.otus.shurupov.spring.introdutionxmlconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.shurupov.spring.introdutionxmlconfig.service.QuizService;

import java.io.IOException;

@ComponentScan
@PropertySource("classpath:application.properties")
public class IntroductionJavaAnnotationConfigurationProject {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IntroductionJavaAnnotationConfigurationProject.class);
        QuizService quizService = context.getBean(QuizService.class);
        quizService.quiz();
    }
}
