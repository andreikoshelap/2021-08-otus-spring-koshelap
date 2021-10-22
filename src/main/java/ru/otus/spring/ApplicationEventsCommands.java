package ru.otus.spring;

import java.io.IOException;
import java.util.Locale;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import ru.otus.spring.domain.Applicant;
import ru.otus.spring.event.publisher.LanguageEventPublisher;
import ru.otus.spring.service.QuizService;
import ru.otus.spring.tools.LocaleProvider;

@ShellComponent
public class ApplicationEventsCommands {

    private final LanguageEventPublisher languageEventPublisher;
    private final QuizService quizService;
    private final LocaleProvider localeProvider;
    private Applicant applicant;
    private Locale locale;

    public ApplicationEventsCommands(LanguageEventPublisher languageEventPublisher, QuizService quizService, LocaleProvider localeProvider) {
        this.languageEventPublisher = languageEventPublisher;
        this.quizService = quizService;
        this.localeProvider = localeProvider;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public void login( @ShellOption(defaultValue = "John") String firstName,
            @ShellOption(defaultValue = "Doe") String lastName) {
        this.applicant = new Applicant(firstName, lastName);
        localeProvider.possibleLocales(applicant);
    }

    @ShellMethod(value = "Pick language for test", key = {"lang", "language"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void pickLanguage(@ShellOption(defaultValue = "EN") String languageKey) {
        this.locale = languageEventPublisher.publishLanguage(languageKey);
    }

    @ShellMethod(value = "Start test", key = { "t" })
    @ShellMethodAvailability(value = "isLanguageChosen")
    public void publishTest() {
        try {
            quizService.executeExam(applicant, locale);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
