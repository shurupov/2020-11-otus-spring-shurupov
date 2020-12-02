package ru.otus.shurupov.spring.introdutionxmlconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.shurupov.spring.introdutionxmlconfig.service.QuestionService;

import java.io.IOException;

@ComponentScan
@PropertySource("classpath:application.properties")
public class IntroductionJavaAnnotationConfigurationProject {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IntroductionJavaAnnotationConfigurationProject.class);
        QuestionService questionService = context.getBean(QuestionService.class);
        questionService.quiz();
    }
}
