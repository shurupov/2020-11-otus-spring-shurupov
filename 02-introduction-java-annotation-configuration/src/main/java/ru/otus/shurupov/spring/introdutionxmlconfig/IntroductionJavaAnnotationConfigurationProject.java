package ru.otus.shurupov.spring.introdutionxmlconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.shurupov.spring.introdutionxmlconfig.domain.Question;
import ru.otus.shurupov.spring.introdutionxmlconfig.service.QuestionsReadService;

import java.io.IOException;
import java.util.List;

@ComponentScan
@PropertySource("classpath:application.properties")
public class IntroductionJavaAnnotationConfigurationProject {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IntroductionJavaAnnotationConfigurationProject.class);
        QuestionsReadService questionsReadService = context.getBean(QuestionsReadService.class);
        List<Question> questions = questionsReadService.readQuestions();
        for (Question question : questions) {
            System.out.print(question);
        }
    }
}
