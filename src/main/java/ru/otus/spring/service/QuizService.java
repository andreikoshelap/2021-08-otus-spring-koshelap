package ru.otus.spring.service;

import java.io.IOException;
import java.util.Locale;

import ru.otus.spring.domain.Applicant;

public interface QuizService {

    void executeExam(Applicant applicant, Locale locale)  throws IOException;
}
