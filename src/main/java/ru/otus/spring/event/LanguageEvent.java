package ru.otus.spring.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import ru.otus.spring.tools.LocaleProvider;

public class LanguageEvent extends ApplicationEvent {

    @Getter
    private final Locale payload;

    public LanguageEvent(Object source, LocaleProvider localeProvider, String key) {
        super(source);
        payload  = localeProvider.defineWorkingLocale(key);
    }
}
