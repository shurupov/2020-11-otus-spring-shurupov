package ru.otus.shurupov.spring.introdutionxmlconfig.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.introdutionxmlconfig.dao.QuestionsDao;
import ru.otus.shurupov.spring.introdutionxmlconfig.domain.Question;

import java.io.IOException;
import java.util.*;

@Service
public class QuestionService {
    private final Random random = new Random();

    private final QuestionsDao questionsDao;
    private final InteractiveService interactiveService;
    private final int quizQuestionCount;
    //Wanted to store this to properties. Couldn't
    private final Map<Double, Integer> scoresToRating = Map.of(
            1.0, 5,
            0.8, 4,
            0.6, 3
    );

    public QuestionService(QuestionsDao questionsDao,
                           InteractiveService interactiveService,
                           @Value("${quiz.questionCount}") int quizQuestionCount) {
        this.interactiveService = interactiveService;
        this.quizQuestionCount = quizQuestionCount;
        this.questionsDao = questionsDao;
    }

    public void quiz() throws IOException {
        List<Question> questions = new LinkedList<>(questionsDao.readQuestions());
        int askedQuestions = 0;
        int correctAnswers = 0;

        interactiveService.println("Enter your first name");
        String firstName = interactiveService.readString();
        interactiveService.println("Enter your last name");
        String lastName = interactiveService.readString();

        do {
            int questionNumber = random.nextInt(questions.size());
            Question question = questions.remove(questionNumber);
            printQuestion(question);
            int answer = interactiveService.readInt();
            interactiveService.println("Your answer is " + question.getAnswers().get(answer));
            interactiveService.println();
            if (answer == question.getCorrectAnswerNumber()) {
                correctAnswers++;
            }
            askedQuestions++;
        } while (askedQuestions < quizQuestionCount && !questions.isEmpty());
        float result = (float) correctAnswers / askedQuestions;
        int rating = getRating(result);
        interactiveService.println(firstName + " " + lastName +", your result is " + rating);
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
                "Answers: " + answersText;
        interactiveService.println(textQuestion);
    }

    private int getRating(float currentScore) {
        for (Map.Entry<Double, Integer> entry : scoresToRating.entrySet()) {
            if (currentScore >= entry.getKey()) {
                return entry.getValue();
            }
        }
        return 2;
    }
}
