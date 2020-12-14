package ru.otus.shurupov.spring.introdutionxmlconfig.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.introdutionxmlconfig.dao.QuestionDao;
import ru.otus.shurupov.spring.introdutionxmlconfig.domain.Question;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuizService {
    private final Random random = new Random();

    private final QuestionDao questionDao;
    private final InteractiveService outputService;
    private final int quizQuestionCount;
    private final Map<Double, Integer> scoresToRating = new LinkedHashMap<>();

    public QuizService(QuestionDao questionDao,
                       InteractiveService outputService,
                       @Value("${quiz.questionCount}") int quizQuestionCount,
                       @Value("${quiz.scores}") String scores) {
        this.outputService = outputService;
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

        outputService.println("Enter your first name");
        String firstName = outputService.readString();
        outputService.println("Enter your last name");
        String lastName = outputService.readString();

        do {
            int questionNumber = random.nextInt(questions.size());
            Question question = questions.remove(questionNumber);
            printQuestion(question);
            int answer = outputService.readInt();
            outputService.println("Your answer is " + question.getAnswers().get(answer));
            outputService.println();
            if (answer == question.getCorrectAnswerNumber()) {
                correctAnswers++;
            }
            askedQuestions++;
        } while (askedQuestions < quizQuestionCount && !questions.isEmpty());
        float result = (float) correctAnswers / askedQuestions;
        int rating = getRating(result);
        outputService.println(firstName + " " + lastName +", your rating is " + rating);
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
        outputService.println(textQuestion);
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
