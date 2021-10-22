package ru.otus.spring.event.publisher;

import java.util.Locale;

public interface LanguageEventPublisher {

    Locale publishLanguage(String key);

}
