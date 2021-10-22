package ru.otus.spring.event;

import java.io.IOException;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import ru.otus.spring.dao.DataMapper;
import ru.otus.spring.ui.Quiz;

public class CsvEvent extends ApplicationEvent {

    @Getter
    private final Quiz payload;

    public CsvEvent(Object source, DataMapper mapper) throws IOException {
        super(source);
        payload = mapper.dataMapping();
    }
}
