package ru.otus.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ru.otus.spring.domain.Applicant;
import ru.otus.spring.domain.QuestionWithAnswers;
import ru.otus.spring.domain.Quiz;

public interface QuizService {

    Quiz executeExam(Applicant applicant, Locale locale, List<QuestionWithAnswers> rows)  throws IOException;

//    Quiz questionsOutput(Applicant applicant, Locale locale);

//    void setLocale(Locale locale);
}
