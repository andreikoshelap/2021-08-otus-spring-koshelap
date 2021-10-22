package ru.otus.spring.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import ru.otus.spring.service.Parser;

@Component
public class ParserImpl implements Parser {


    private final String csvFile;
    private static final char DELIMITER = ';';

    public ParserImpl(@Value("${classpath:questionare.csv}") String csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public CSVParser getParsedCsv() throws IOException {
        InputStream is = new ClassPathResource(csvFile).getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return CSVFormat.DEFAULT.withDelimiter(DELIMITER).withHeader().parse(br);
    }
}
