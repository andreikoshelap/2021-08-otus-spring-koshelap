package ru.otus.spring.service;

import java.io.IOException;

import org.apache.commons.csv.CSVParser;

public interface Parser {

    CSVParser getParsedCsv() throws IOException;

}
