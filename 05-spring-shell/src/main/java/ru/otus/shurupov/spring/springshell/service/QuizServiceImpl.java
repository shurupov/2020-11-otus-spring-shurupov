package ru.otus.shurupov.spring.springshell.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.spring.springshell.aop.QuizLogging;
import ru.otus.shurupov.spring.springshell.config.QuizProps;
import ru.otus.shurupov.spring.springshell.dao.QuestionDao;
import ru.otus.shurupov.spring.springshell.domain.Question;
import ru.otus.shurupov.spring.springshell.utils.QuizState;

import java.io.IOException;
import java.util.*;

@Service
public class QuizServiceImpl implements QuizService {
    private final Random random = new Random();

    private final QuestionDao questionDao;
    private final InteractiveService interactiveService;
    private final MessageSource messageSource;
    private final int quizQuestionCount;
    private final Locale locale;
    private final Map<Double, Integer> scoresToRating;

    private String firstName;
    private String lastName;

    private QuizState state = QuizState.ENTERING_NAME;

    private List<Question> questions;
    private Question question;

    private int askedQuestions = 0;
    private int correctAnswers = 0;

    public QuizServiceImpl(QuestionDao questionDao,
                           InteractiveService interactiveService,
                           MessageSource messageSource, QuizProps quizProps) {
        this.interactiveService = interactiveService;
        this.messageSource = messageSource;
        this.questionDao = questionDao;
        this.quizQuestionCount = quizProps.getQuestions().getCount();
        this.locale = quizProps.getLocale();
        this.scoresToRating = quizProps.getScores();
    }

    public QuizState getState() {
        return state;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        if (lastName == null) {
            System.out.println("Enter also last name");
        } else {
            nameEntered();
        }
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        if (this.firstName == null) {
            System.out.println("Enter also first name");
        } else {
            nameEntered();
        }
    }

    public void setFullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        nameEntered();
    }

    private void nameEntered() {
        state = QuizState.QUESTIONS;
        System.out.println("Hello, " + this.firstName + " " + this.lastName + "!");
        startQuiz();
    }

    private void startQuiz() {
        try {
            questions = new LinkedList<>(questionDao.readQuestions());
            askQuestion();
        } catch (IOException e) {
            throw new RuntimeException("Questions can't be read");
        }
    }

    private void askQuestion() {
        int questionNumber = random.nextInt(questions.size());
        question = questions.remove(questionNumber);
        printQuestion(question);
    }

    public void answer(int answerNumber) {
        interactiveService.println(messageSource.getMessage("Your answer is", new String[] { question.getAnswers().get(answerNumber) }, locale));
        interactiveService.println();
        if (answerNumber == question.getCorrectAnswerNumber()) {
            correctAnswers++;
        }
        askedQuestions++;
        if (askedQuestions < quizQuestionCount && !questions.isEmpty()) {
            askQuestion();
        } else {
            float result = (float) correctAnswers / askedQuestions;
            int rating = getRating(result);
            interactiveService.println(messageSource.getMessage("your rating is", new Object[] {firstName, lastName, rating }, locale));
        }
    }

    @QuizLogging
    public void quiz() throws IOException {
        questions = new LinkedList<>(questionDao.readQuestions());
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
