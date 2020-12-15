package ru.otus.shurupov.spring.springshell.service;

import org.springframework.stereotype.Service;
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
    private final OutputService outputService;
    private final int quizQuestionCount;
    private final Map<Double, Integer> scoresToRating;

    private String firstName;
    private String lastName;

    private QuizState state;

    private List<Question> questions;
    private Question question;

    private int askedQuestions;
    private int correctAnswers;

    public QuizServiceImpl(QuestionDao questionDao,
                           OutputService outputService,
                           QuizProps quizProps) {
        this.outputService = outputService;
        this.questionDao = questionDao;
        this.quizQuestionCount = quizProps.getQuestions().getCount();
        this.scoresToRating = quizProps.getScores();
        startIntroduction();
    }

    public QuizState getState() {
        return state;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        if (lastName == null) {
            outputService.println("Enter also last name");
        } else {
            startQuiz();
        }
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        if (this.firstName == null) {
            outputService.println("Enter also first name");
        } else {
            startQuiz();
        }
    }

    public void setFullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        startQuiz();
    }

    protected void startQuiz() {
        try {
            state = QuizState.QUESTIONS;
            outputService.println("Hello", this.firstName, this.lastName);
            askedQuestions = 0;
            correctAnswers = 0;
            questions = new LinkedList<>(questionDao.readQuestions());
            askQuestion();
        } catch (IOException e) {
            throw new RuntimeException("Questions can't be read");
        }
    }

    protected void askQuestion() {
        int questionNumber = random.nextInt(questions.size());
        question = questions.remove(questionNumber);
        printQuestion(question);
    }

    public void answer(int answerNumber) {
        outputService.println("Your answer is", question.getAnswers().get(answerNumber));
        outputService.println();
        if (answerNumber == question.getCorrectAnswerNumber()) {
            correctAnswers++;
        }
        askedQuestions++;
        if (askedQuestions < quizQuestionCount && !questions.isEmpty()) {
            askQuestion();
        } else {
            float result = (float) correctAnswers / askedQuestions;
            int rating = getRating(result);
            outputService.println("your rating is", firstName, lastName, rating);
            startIntroduction();
        }
    }

    protected void printQuestion(Question question) {
        StringBuilder answersText = new StringBuilder();
        for (int i = 0; i < question.getAnswers().size(); i++) {
            answersText.append(i + 1).append(") ").append(question.getAnswers().get(i));
            if (i != question.getAnswers().size() - 1) {
                answersText.append("; ");
            }
        }
        outputService.println("Question", question.getQuestion(), answersText.toString());
    }

    protected int getRating(float currentScore) {
        for (Map.Entry<Double, Integer> entry : scoresToRating.entrySet()) {
            if (currentScore >= entry.getKey()) {
                return entry.getValue();
            }
        }
        return 2;
    }

    protected void startIntroduction() {
        state = QuizState.ENTERING_NAME;
    }
}
