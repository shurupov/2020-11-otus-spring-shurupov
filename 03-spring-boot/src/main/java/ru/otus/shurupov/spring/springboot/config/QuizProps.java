package ru.otus.shurupov.spring.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ConfigurationProperties(prefix = "quiz")
public class QuizProps {
    private Questions questions;
    private Map<Double, Integer> scoresToRating;
    private Locale locale;

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public Map<Double, Integer> getScores() {
        return scoresToRating;
    }

    public void setScores(String scores) {
        List<Double> scoresList = Stream.of(scores.split(",")).map(Double::parseDouble).collect(Collectors.toList());
        scoresToRating = new LinkedHashMap<>();
        for (int i = 0; i < scoresList.size(); i++) {
            scoresToRating.put(scoresList.get(i), 5 - i);
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public static class Questions {
        private String path;
        private int count;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
