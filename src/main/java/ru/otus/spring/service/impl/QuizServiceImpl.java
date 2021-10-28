package ru.otus.spring.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import ru.otus.spring.aspect.LogExecutionTime;
import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.domain.Applicant;
import ru.otus.spring.domain.QuestionWithAnswers;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.service.QuizService;
import ru.otus.spring.tools.InputOutputService;

@Service
public class QuizServiceImpl implements QuizService {

    private static final String ANSWER_NO_ONE = "A.";
    private static final String ANSWER_NO_TWO = "B.";
    private static final String ANSWER_NO_THREE = "C.";
    private static final String ANSWER_NO_FOUR = "D.";
    private static final String ANSWER_NO_FIVE = "E.";
    private static final String ANSWER_NO_SIX = "F.";
    private static final String QUESTION_TYPE_WITH_ONE_ANSWER = "1";
    private static final String QUESTION_TYPE_WITH_MULTIPLE_ANSWERS = "2";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private static final String QUIZ_RESULT = "quiz.result";
    private static final String ENTERED_ANSWER = "entered.answer";
    private static final String CORRECT_ANSWER = "correct.answer";
    private static final String INSERT_ONE_ANSWER = "insert.one.answer";
    private static final String INSERT_MULTIPLE_ANSWER = "insert.multiple.answer";

    private MessageSource msg;
    private List<Double> scores = new ArrayList<>();
    private Locale locale;
    private InputOutputService ioService;

    public QuizServiceImpl( MessageSource msg, InputOutputService ioService, DataMapper mapper) {
        this.msg = msg;
        this.ioService = ioService;
    }

    @LogExecutionTime
    @Override
    public Quiz executeExam(Applicant applicant, Locale locale, List<QuestionWithAnswers> rows) {
        this.locale = locale;
        rows.stream().forEach(p -> {
            ioService.out(p.getNo());
            ioService.out(p.getQuestion());
            ioService.out("");
            ioService.out(ANSWER_NO_ONE + p.getAnswerA());
            ioService.out(ANSWER_NO_TWO + p.getAnswerB());
            ioService.out(ANSWER_NO_THREE + p.getAnswerC());
            ioService.out(ANSWER_NO_FOUR + p.getAnswerD());
            ioService.out(ANSWER_NO_FIVE + p.getAnswerE());
            if (p.getAnswerF() != null && !p.getAnswerF().isEmpty()) {
                ioService.out(ANSWER_NO_SIX + p.getAnswerF());
            }
            acceptAndMatchAnswer(p);
        });
        output(QUIZ_RESULT, new String[]{ ANSI_RED, String.valueOf(getResult(scores)), ANSI_RESET });
        return new Quiz(applicant, scores, locale);
    }

    private int getResult(List<Double> scores) {
        return (int) Math.round(scores.stream().reduce(0D, Double::sum) * 100D / Double.valueOf(scores.size()));
    }

    private void acceptAndMatchAnswer(QuestionWithAnswers p) {
        getDelimiterLine(p);
        String userAnswer = ioService.readString();
        output(ENTERED_ANSWER, new String[]{ ANSI_BLUE, userAnswer.toUpperCase(), ANSI_RESET });
        output(CORRECT_ANSWER, new String[]{ ANSI_BLUE, p.getCorrectAnswer(), ANSI_RESET });
        scoringAnswer(userAnswer, p.getCorrectAnswer(), p.getType());
    }

    private void scoringAnswer(String userAnswer, String correctAnswer, String questionType) {
        if (questionType.equals(QUESTION_TYPE_WITH_ONE_ANSWER) && userAnswer.compareToIgnoreCase(correctAnswer) == 0) {
            scores.add(1D);
            return;
        }
        if (questionType.equals(QUESTION_TYPE_WITH_MULTIPLE_ANSWERS)) {
            scores.add(scoringMultipleAnswers(userAnswer, correctAnswer));
            return;
        }
        scores.add(0D);
    }

    private Double scoringMultipleAnswers(String userAnswer, String correctAnswer) {
        String[] userAnswers = userAnswer.split(";");
        List<String> userAnswersUppercase = Arrays.asList(userAnswers).stream()
                .map(ua -> ua.toUpperCase()).collect(Collectors.toList());
        String[] correctAnswers = correctAnswer.split(";");
        if (Arrays.asList(correctAnswers).equals(userAnswersUppercase)) {
            return 1D;
        }
        return 0D;
    }

    private void getDelimiterLine(QuestionWithAnswers p) {
        if (p.getType().equals(QUESTION_TYPE_WITH_ONE_ANSWER)) {
            output(INSERT_ONE_ANSWER, new String[]{ ANSI_RED, ANSI_RESET });
            return;
        }
        output(INSERT_MULTIPLE_ANSWER, new String[]{ ANSI_RED, ANSI_RESET });
    }

    private void output(String propertyString, String[] msgParameters) {
        ioService.out(msg.getMessage(propertyString, msgParameters, locale));
    }
}
