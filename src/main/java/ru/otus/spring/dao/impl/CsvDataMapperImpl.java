package ru.otus.spring.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.domain.QuestionWithAnswers;
import ru.otus.spring.service.Parser;

@Component
public class CsvDataMapperImpl implements DataMapper {

    private Parser parser;

    public CsvDataMapperImpl(Parser parser) {
        this.parser = parser;
    }

    @Override
    public List<QuestionWithAnswers> dataMapping() throws IOException {
        List<QuestionWithAnswers> rows = new ArrayList<>();
        parser.getParsedCsv().forEach( record -> {
            QuestionWithAnswers row = new QuestionWithAnswers();
            row.setNo(record.get("no"));
            row.setType(record.get("type"));
            row.setQuestion(record.get("question"));
            row.setAnswerA(record.get("A"));
            row.setAnswerB(record.get("B"));
            row.setAnswerC(record.get("C"));
            row.setAnswerD(record.get("D"));
            row.setAnswerE(record.get("E"));
            row.setAnswerF(record.get("F"));
            row.setCorrectAnswer(record.get("correct"));
            rows.add(row);
        });
        return rows;
    }

}
