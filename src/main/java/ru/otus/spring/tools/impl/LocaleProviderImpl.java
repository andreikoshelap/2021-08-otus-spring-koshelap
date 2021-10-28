package ru.otus.spring.tools.impl;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import ru.otus.spring.domain.Applicant;
import ru.otus.spring.tools.InputOutputService;
import ru.otus.spring.tools.LocaleProvider;

@Component
public class LocaleProviderImpl implements LocaleProvider {

    private static final String TITLE_LANGUAGE = "title.language";
    private static final String ET = "et";
    private static final String EE = "EE";
    private static final String SMALL_RU = "ru";
    private static final String RU = "RU";
    private static final String PICKED_LANGUAGE = "picked.language";
    private static final Set<Locale> AVAILABLE_LOCALES = Set.of(
            new Locale(SMALL_RU, RU),
            new Locale(ET, EE),
            Locale.ENGLISH);
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private MessageSource msg;
    private InputOutputService ioService;
    private Locale locale;

    public LocaleProviderImpl(MessageSource msg, InputOutputService ioService) {
        this.msg = msg;
        this.ioService = ioService;
    }

    @Override
    public Locale defineWorkingLocale(String key) {
        locale = Locale.ENGLISH;
        switch (key.toLowerCase()) {
            case ET:
                locale = new Locale(ET, EE);
                break;
            case SMALL_RU:
                locale = new Locale(SMALL_RU, RU);
                break;
            default:
        }

        ioService.out(msg.getMessage(PICKED_LANGUAGE, new String[]{ ANSI_BLUE, ANSI_RESET }, locale));
        return this.locale;
    }

    @Override
    public void possibleLocales(Applicant applicant) {
        ioService.out(String.format("Welcome, %s %s", applicant.getFirstName(), applicant.getLastName()));
        AVAILABLE_LOCALES.stream()
                .forEach(loc -> ioService.out(msg.getMessage(TITLE_LANGUAGE, new String[]{ ANSI_BLUE, ANSI_RESET }, loc)));

    }

    @Override
    public void getLocalizedString(String s, String... args) {
        ioService.out(msg.getMessage(s, new String[]{ ANSI_BLUE, ANSI_RESET }, locale));
        Arrays.stream(args).forEach(ioService::out);
    }
}
