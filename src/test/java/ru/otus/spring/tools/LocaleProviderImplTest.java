package ru.otus.spring.tools;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.spring.domain.Applicant;
import ru.otus.spring.tools.impl.LocaleProviderImpl;

@ExtendWith(SpringExtension.class)
public class LocaleProviderImplTest {

    private LocaleProvider localeProvider;
    @Mock
    private MessageSource msg;
    @Mock
    private InputOutputService ioService;

    @BeforeEach
    void setUp() {
        localeProvider = new LocaleProviderImpl(msg, ioService);
    }

    @Test
    public void pickWorkingLocale() {
        Locale result = localeProvider.defineWorkingLocale("ru");

        assertThat(result.getCountry()).isEqualTo("RU");
    }
    @Test
    public void outputPossibleLocalies() {
        localeProvider.possibleLocales(new Applicant("John", "Doe"));

        verify(ioService, times(4)).out(any());
    }
}
