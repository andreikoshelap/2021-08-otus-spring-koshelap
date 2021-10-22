package ru.otus.spring.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.domain.QuestionWithAnswers;
import ru.otus.spring.service.Parser;
import ru.otus.spring.tools.InputOutputService;
import ru.otus.spring.ui.Quiz;
import ru.otus.spring.ui.impl.QuizImpl;

@Component
public class CsvDataMapperImpl implements DataMapper {

    private Parser parser;
    private MessageSource msg;
    private InputOutputService ioService;

    public CsvDataMapperImpl(Parser parser, MessageSource msg, InputOutputService ioService) {
        this.parser = parser;
        this.msg = msg;
        this.ioService = ioService;
    }

    @Override
    public Quiz dataMapping() throws IOException {
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
        return new QuizImpl(rows, msg, ioService);
    }

}
