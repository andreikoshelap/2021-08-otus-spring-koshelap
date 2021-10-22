package ru.otus.spring.tools.impl;

import java.io.PrintStream;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import ru.otus.spring.tools.InputOutputService;

@Service
public class ConsoleInputOutputImpl implements InputOutputService {

    private final PrintStream out;
    private final Scanner sc;

    public ConsoleInputOutputImpl() {
        this.out = System.out;
        this.sc = new Scanner(System.in);
    }

    @Override
    public void out(String message) {
        out.println(message);
    }

    @Override
    public String readString() {
        return sc.nextLine();
    }
}
