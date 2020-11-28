package ru.otus.shurupov.spring.introdutionxmlconfig;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.shurupov.spring.introdutionxmlconfig.service.QuestionsReadService;

import java.io.IOException;

public class IntroductionXmlConfigurationProject {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionsReadService questionsReadService = context.getBean(QuestionsReadService.class);
        questionsReadService.readQuestions();
    }
}
