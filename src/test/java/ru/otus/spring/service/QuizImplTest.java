package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.domain.QuestionWithAnswers;
import ru.otus.spring.tools.InputOutputService;
import ru.otus.spring.ui.Quiz;
import ru.otus.spring.ui.impl.QuizImpl;

@ExtendWith(SpringExtension.class)
public class QuizImplTest {

    private Quiz quiz;
    @Mock
    private MessageSource msg;
    @Mock
    private InputOutputService ioService;

    private List<QuestionWithAnswers> rows = new ArrayList<>();

    @BeforeEach
    void setUp() {
        QuestionWithAnswers questionWithAnswers = new QuestionWithAnswers();
        questionWithAnswers.setNo("1");
        questionWithAnswers.setType("1");
        questionWithAnswers.setQuestion("question subj one");
        questionWithAnswers.setAnswerA("answer one");
        questionWithAnswers.setAnswerB("answer two");
        questionWithAnswers.setAnswerC("answer three");
        questionWithAnswers.setAnswerD("answer four");
        questionWithAnswers.setAnswerE("answer five");
        questionWithAnswers.setCorrectAnswer("A");
        rows.add(questionWithAnswers);
        quiz = new QuizImpl(rows, msg, ioService);
    }

    @Test
    public void shouldGiveOneCorrectAnswer() {
        String input = "a";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        quiz.questionsOutput();

        assertThat(quiz.getScores()).isNotEmpty().containsExactly(1D);

    }

    @Test
    public void shouldGiveOneWrongAnswer() {
        String input = "C";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        quiz.questionsOutput();

        assertThat(quiz.getScores()).isNotEmpty().containsExactly(0D);

    }
}
