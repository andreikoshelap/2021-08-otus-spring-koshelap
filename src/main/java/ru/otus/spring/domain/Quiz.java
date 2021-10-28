package ru.otus.spring.domain;

import java.util.List;
import java.util.Locale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quiz {

    private final Applicant applicant;
    private List<Double> scores;
    private Locale locale;

    public Quiz(Applicant applicant, List<Double> scores, Locale locale) {
        this.applicant = applicant;
        this.scores = scores;
        this.locale = locale;
    }

}
