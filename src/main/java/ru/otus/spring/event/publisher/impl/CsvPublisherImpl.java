package ru.otus.spring.event.publisher.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.domain.QuestionWithAnswers;
import ru.otus.spring.event.CsvEvent;
import ru.otus.spring.event.LanguageEvent;
import ru.otus.spring.event.publisher.CsvPublisher;

@Service
@RequiredArgsConstructor
public class CsvPublisherImpl implements CsvPublisher {

    private final ApplicationEventPublisher publisher;
    @Autowired
    private DataMapper mapper;

    @Override
    public List<QuestionWithAnswers> prepareQiuz() throws IOException {
        CsvEvent event = new CsvEvent(this, mapper);
        publisher.publishEvent(new CsvEvent(this, mapper));
        return event.getPayload();
    }
}
