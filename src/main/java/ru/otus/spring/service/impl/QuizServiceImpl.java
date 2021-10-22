package ru.otus.spring.service.impl;

import java.io.IOException;
import java.util.Locale;

import org.springframework.stereotype.Service;

import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.domain.Applicant;
import ru.otus.spring.service.QuizService;
import ru.otus.spring.ui.Quiz;

@Service
public class QuizServiceImpl implements QuizService {
    private DataMapper mapper;

    public QuizServiceImpl(DataMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void executeExam(Applicant applicant, Locale locale) throws IOException {
        Quiz quiz = getQuiz();
        quiz.setLocale(locale);
        output(quiz, applicant);
    }

    private Quiz getQuiz() throws IOException {
        return mapper.dataMapping();
    }

    private void output(Quiz quiz, Applicant applicant) {
        quiz.questionsOutput(applicant);
    }
}
