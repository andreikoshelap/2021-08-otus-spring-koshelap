package ru.otus.spring.dao;

import java.io.IOException;

import ru.otus.spring.ui.Quiz;

public interface DataMapper {

    Quiz dataMapping() throws IOException;
}
