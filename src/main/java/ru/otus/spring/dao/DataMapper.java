package ru.otus.spring.dao;

import java.io.IOException;
import java.util.List;

import ru.otus.spring.domain.QuestionWithAnswers;

public interface DataMapper {

    List<QuestionWithAnswers> dataMapping() throws IOException;
}
