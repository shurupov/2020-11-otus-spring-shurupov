package ru.otus.shurupov.spring.introdutionxmlconfig.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class QuestionsReadService {

    private static final String QUESTIONS_FILE_NAME = "questions.csv";

    public void readQuestions() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(QUESTIONS_FILE_NAME).getFile());
        String data = FileUtils.readFileToString(file, "UTF-8");
        System.out.println(data);
    }

}
