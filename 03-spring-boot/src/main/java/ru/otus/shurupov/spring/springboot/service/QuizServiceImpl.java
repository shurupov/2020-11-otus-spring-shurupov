package ru.otus.shurupov.spring.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
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
    private final MessageSource messageSource;
    private final int quizQuestionCount;
    private final Locale locale;
    private final Map<Double, Integer> scoresToRating = new LinkedHashMap<>();

    public QuizServiceImpl(QuestionDao questionDao,
                           InteractiveService interactiveService,
                           MessageSource messageSource, @Value("${quiz.questions.count}") int quizQuestionCount,
                           @Value("${quiz.scores}") String scores,
                           @Value("${quiz.locale}") Locale locale) {
        this.interactiveService = interactiveService;
        this.messageSource = messageSource;
        this.quizQuestionCount = quizQuestionCount;
        this.questionDao = questionDao;
        this.locale = locale;
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

        interactiveService.println(messageSource.getMessage("Enter your first name", new String[] {}, locale));
        String firstName = interactiveService.readString();
        interactiveService.println(messageSource.getMessage("Enter your last name", new String[] {}, locale));
        String lastName = interactiveService.readString();

        do {
            int questionNumber = random.nextInt(questions.size());
            Question question = questions.remove(questionNumber);
            printQuestion(question);
            int answer = interactiveService.readInt();
            interactiveService.println(messageSource.getMessage("Your answer is", new String[] { question.getAnswers().get(answer) }, locale));
            interactiveService.println();
            if (answer == question.getCorrectAnswerNumber()) {
                correctAnswers++;
            }
            askedQuestions++;
        } while (askedQuestions < quizQuestionCount && !questions.isEmpty());
        float result = (float) correctAnswers / askedQuestions;
        int rating = getRating(result);
        interactiveService.println(messageSource.getMessage("your rating is", new Object[] {firstName, lastName, rating }, locale));
    }

    protected void printQuestion(Question question) {
        StringBuilder answersText = new StringBuilder();
        for (int i = 0; i < question.getAnswers().size(); i++) {
            answersText.append(i + 1).append(") ").append(question.getAnswers().get(i));
            if (i != question.getAnswers().size() - 1) {
                answersText.append("; ");
            }
        }
        String textQuestion = messageSource.getMessage("Question", new Object[] { question.getQuestion(), answersText }, locale);
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
