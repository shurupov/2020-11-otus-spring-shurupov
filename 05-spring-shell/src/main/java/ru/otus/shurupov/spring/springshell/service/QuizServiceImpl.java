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

    private QuizState state = QuizState.ENTERING_NAME;

    private List<Question> questions;
    private Question question;

    private int askedQuestions = 0;
    private int correctAnswers = 0;

    public QuizServiceImpl(QuestionDao questionDao,
                           OutputService outputService,
                           QuizProps quizProps) {
        this.outputService = outputService;
        this.questionDao = questionDao;
        this.quizQuestionCount = quizProps.getQuestions().getCount();
        this.scoresToRating = quizProps.getScores();
    }

    public QuizState getState() {
        return state;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        if (lastName == null) {
            outputService.printMessage("Enter also last name");
        } else {
            nameEntered();
        }
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        if (this.firstName == null) {
            outputService.printMessage("Enter also first name");
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
        outputService.printMessage("Hello", this.firstName, this.lastName);
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
        outputService.printMessage("Your answer is", question.getAnswers().get(answerNumber));
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
            outputService.printMessage("your rating is", firstName, lastName, rating);
            quit();
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
        outputService.printMessage("Question", question.getQuestion(), answersText.toString());
    }

    protected int getRating(float currentScore) {
        for (Map.Entry<Double, Integer> entry : scoresToRating.entrySet()) {
            if (currentScore >= entry.getKey()) {
                return entry.getValue();
            }
        }
        return 2;
    }

    protected void quit() {
        System.exit(0);
    }
}
