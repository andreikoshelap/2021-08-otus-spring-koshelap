package ru.otus.spring.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizResult {

    private final Applicant applicant;
    private List<Double> scores;

    public QuizResult(Applicant applicant, List<Double> scores) {
        this.applicant = applicant;
        this.scores = scores;
    }

}
