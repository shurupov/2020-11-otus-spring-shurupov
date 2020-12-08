package ru.otus.shurupov.spring.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.springboot.dao.QuestionDao;
import ru.otus.shurupov.spring.springboot.domain.Question;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuizServiceImpl implements QuizService {
    private final Random random = new Random();

    private final QuestionDao questionDao;
    private final InteractiveService interactiveService;
    private final int quizQuestionCount;
    private final Map<Double, Integer> scoresToRating = new LinkedHashMap<>();

    public QuizServiceImpl(QuestionDao questionDao,
                           InteractiveService interactiveService,
                           @Value("${quiz.questionCount}") int quizQuestionCount,
                           @Value("${quiz.scores}") String scores) {
        this.interactiveService = interactiveService;
        this.quizQuestionCount = quizQuestionCount;
        this.questionDao = questionDao;
        initScores(scores);
    }

    private void initScores(String scores) {
        List<Double> scoresList = Stream.of(scores.split(",")).map(Double::parseDouble).collect(Collectors.toList());
        for (int i = 0; i < scoresList.size(); i++) {
            scoresToRating.put(scoresList.get(i), 5 - i);
        }
    }

    public void quiz() throws IOException {
        List<Question> questions = new LinkedList<>(questionDao.readQuestions());
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
        interactiveService.println(firstName + " " + lastName +", your rating is " + rating);
    }

    protected void printQuestion(Question question) {
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

    protected int getRating(float currentScore) {
        for (Map.Entry<Double, Integer> entry : scoresToRating.entrySet()) {
            if (currentScore >= entry.getKey()) {
                return entry.getValue();
            }
        }
        return 2;
    }
}
