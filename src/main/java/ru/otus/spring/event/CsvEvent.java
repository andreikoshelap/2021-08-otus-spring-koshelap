package ru.otus.spring.event;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.domain.QuestionWithAnswers;

public class CsvEvent extends ApplicationEvent {

    @Getter
    private final List<QuestionWithAnswers> payload;

    public CsvEvent(Object source, DataMapper mapper) throws IOException {
        super(source);
        payload = mapper.dataMapping();
    }
}
