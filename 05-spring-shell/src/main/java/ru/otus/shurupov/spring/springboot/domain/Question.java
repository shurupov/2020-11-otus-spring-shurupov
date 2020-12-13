package ru.otus.shurupov.spring.springboot.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Question {
    private final String question;
    private final List<String> answers;
    private final int correctAnswerNumber;

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
