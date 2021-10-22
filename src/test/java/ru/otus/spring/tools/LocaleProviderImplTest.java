package ru.otus.spring.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

}
