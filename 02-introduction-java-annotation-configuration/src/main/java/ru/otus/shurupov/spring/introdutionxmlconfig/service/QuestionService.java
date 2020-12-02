package ru.otus.shurupov.spring.introdutionxmlconfig.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.introdutionxmlconfig.dao.QuestionsDao;
import ru.otus.shurupov.spring.introdutionxmlconfig.domain.Question;

import java.io.IOException;
import java.util.*;

@Service
public class QuestionService {
    private final int quizQuestionCount;

    private final QuestionsDao questionsDao;

    private final Scanner scanner = new Scanner(System.in);

    public QuestionService(QuestionsDao questionsDao, @Value("${quiz.questionCount}") int quizQuestionCount) {
        this.quizQuestionCount = quizQuestionCount;
        this.questionsDao = questionsDao;
    }

    public void quiz() throws IOException {
        List<Question> questions = new LinkedList<>(questionsDao.readQuestions());
        int askedQuestions = 0;
        int correctAnswers = 0;
        Random random = new Random();
        do {
            int questionNumber = random.nextInt(questions.size());
            Question question = questions.remove(questionNumber);
            printQuestion(question);
            int answer = getAnswer();
            System.out.println("Your answer is " + question.getAnswers().get(answer));
            System.out.println();
            if (answer == question.getCorrectAnswerNumber()) {
                correctAnswers++;
            }
            askedQuestions++;
        } while (askedQuestions < quizQuestionCount && !questions.isEmpty());
        float result = (float) correctAnswers / askedQuestions;
        System.out.println("Your result is " + result);
    }

    private int getAnswer() {
        return scanner.nextInt() - 1;
    }

    private void printQuestion(Question question) {
        StringBuilder answersText = new StringBuilder();
        for (int i = 0; i < question.getAnswers().size(); i++) {
            answersText.append(i + 1).append(") ").append(question.getAnswers().get(i));
            if (i != question.getAnswers().size() - 1) {
                answersText.append("; ");
            }
        }
        String textQuestion = "Question: " + question.getQuestion() + "\n" +
                "Answers: " + answersText + "\n";
        System.out.print(textQuestion);
    }
}
