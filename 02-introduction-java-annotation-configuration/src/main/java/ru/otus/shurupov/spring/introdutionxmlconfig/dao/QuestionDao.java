package ru.otus.shurupov.spring.introdutionxmlconfig.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.introdutionxmlconfig.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionDao {

    private final String questionsFileName;

    public QuestionDao(@Value("${questions.path}") String questionsFileName) {
        this.questionsFileName = questionsFileName;
    }

    public List<Question> readQuestions() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream csvStream = classLoader.getResource(questionsFileName).openStream();
        Reader in = new InputStreamReader(csvStream);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(new String[]{"question", "answer1", "answer2", "answer3", "correctAnswer"}).parse(in);
        List<Question> questions = new ArrayList<>();
        for (CSVRecord record : records) {
            Question question = new Question(
                    record.get("question"),
                    Arrays.asList(record.get("answer1"), record.get("answer2"), record.get("answer3")),
                    Integer.parseInt(record.get("correctAnswer"))
            );
            questions.add(question);
        }
        return questions;
    }
}
