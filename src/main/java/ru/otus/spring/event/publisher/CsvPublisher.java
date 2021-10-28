package ru.otus.spring.event.publisher;

import java.io.IOException;
import java.util.List;

import ru.otus.spring.domain.QuestionWithAnswers;

public interface CsvPublisher {

    List<QuestionWithAnswers> prepareQiuz() throws IOException;
}
