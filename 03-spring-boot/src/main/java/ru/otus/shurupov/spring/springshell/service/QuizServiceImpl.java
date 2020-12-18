package ru.otus.shurupov.spring.springshell.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.springshell.aop.QuizLogging;
import ru.otus.shurupov.spring.springshell.config.QuizProps;
import ru.otus.shurupov.spring.springshell.dao.QuestionDao;
import ru.otus.shurupov.spring.springshell.domain.Question;

import java.io.IOException;
import java.util.*;

@Service
public class QuizServiceImpl implements QuizService {
    private final Random random = new Random();

    private final QuestionDao questionDao;
    private final InteractiveService outputService;
    private final MessageSource messageSource;
    private final int quizQuestionCount;
    private final Locale locale;
    private final Map<Double, Integer> scoresToRating;

    public QuizServiceImpl(QuestionDao questionDao,
                           InteractiveService outputService,
                           MessageSource messageSource, QuizProps quizProps) {
        this.outputService = outputService;
        this.messageSource = messageSource;
        this.questionDao = questionDao;
        this.quizQuestionCount = quizProps.getQuestions().getCount();
        this.locale = quizProps.getLocale();
        this.scoresToRating = quizProps.getScores();
    }

    @QuizLogging
    public void quiz() throws IOException {
        List<Question> questions = new LinkedList<>(questionDao.readQuestions());
        int askedQuestions = 0;
        int correctAnswers = 0;

        outputService.println(messageSource.getMessage("Enter your first name", new String[] {}, locale));
        String firstName = outputService.readString();
        outputService.println(messageSource.getMessage("Enter your last name", new String[] {}, locale));
        String lastName = outputService.readString();

        do {
            int questionNumber = random.nextInt(questions.size());
            Question question = questions.remove(questionNumber);
            printQuestion(question);
            int answer = outputService.readInt();
            outputService.println(messageSource.getMessage("Your answer is", new String[] { question.getAnswers().get(answer) }, locale));
            outputService.println();
            if (answer == question.getCorrectAnswerNumber()) {
                correctAnswers++;
            }
            askedQuestions++;
        } while (askedQuestions < quizQuestionCount && !questions.isEmpty());
        float result = (float) correctAnswers / askedQuestions;
        int rating = getRating(result);
        outputService.println(messageSource.getMessage("your rating is", new Object[] {firstName, lastName, rating }, locale));
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
