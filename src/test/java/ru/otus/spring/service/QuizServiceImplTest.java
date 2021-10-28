package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.domain.Applicant;
import ru.otus.spring.domain.QuestionWithAnswers;
import ru.otus.spring.domain.Quiz;
import ru.otus.spring.service.impl.QuizServiceImpl;
import ru.otus.spring.tools.InputOutputService;

@ExtendWith(SpringExtension.class)
public class QuizServiceImplTest {

    private QuizService quizService;
    @Mock
    private MessageSource msg;
    @Mock
    private InputOutputService ioService;
    @Mock
    private DataMapper mapper;

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
        quizService = new QuizServiceImpl(msg, ioService, mapper);
    }

    @Test
    public void shouldGiveOneCorrectAnswer() throws IOException {
        String input = "a";
        Applicant applicant = new Applicant("John", "Doe");
        when(ioService.readString()).thenReturn(input);

        Quiz result = quizService.executeExam(applicant, Locale.ENGLISH, rows);

        assertThat(result.getScores()).isNotEmpty().containsExactly(1D);
    }

    @Test
    public void shouldGiveOneWrongAnswer() throws IOException {
        String input = "b";
        Applicant applicant = new Applicant("John", "Doe");
        when(ioService.readString()).thenReturn(input);

        Quiz result = quizService.executeExam(applicant, Locale.ENGLISH, rows);

        assertThat(result.getScores()).isNotEmpty().containsExactly(0D);
    }
}
