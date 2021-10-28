package ru.otus.spring.tools;

import java.util.Locale;

import ru.otus.spring.domain.Applicant;

public interface LocaleProvider {

    Locale defineWorkingLocale(String  key);

    void possibleLocales(Applicant applicant);

    void getLocalizedString(String s, String... args);
}
