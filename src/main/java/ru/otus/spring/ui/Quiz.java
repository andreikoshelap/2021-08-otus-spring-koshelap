package ru.otus.spring.ui;

import java.util.Locale;

import ru.otus.spring.domain.Applicant;
import ru.otus.spring.domain.QuizResult;

public interface Quiz {

    QuizResult questionsOutput(Applicant applicant);

    void setLocale(Locale locale);
}
