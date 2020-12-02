package ru.otus.shurupov.spring.introdutionxmlconfig.domain;

import java.util.Collections;
import java.util.List;

public class Question {
    private final String question;
    private final List<String> answers;
    private final int correctAnswerNumber;

    public Question(String question, List<String> answers, int correctAnswerNumber) {
        this.question = question;
        this.answers = Collections.unmodifiableList(answers);
        this.correctAnswerNumber = correctAnswerNumber;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    @Override
    public String toString() {
        StringBuilder answersText = new StringBuilder();
        for (int i = 0; i < answers.size(); i++) {
            answersText.append(i + 1).append(") ").append(answers.get(i));
            if (i != answers.size() - 1) {
                answersText.append("; ");
            }
        }
        return "Question: " + question + "\n" +
               "Answers: " + answersText + "\n";
    }
}
