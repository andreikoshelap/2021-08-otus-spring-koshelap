package ru.otus.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import ru.otus.spring.tools.LocaleProvider;

@Aspect
@Component
public class TimerAspect {


    private final LocaleProvider localeProvider;

    public TimerAspect(LocaleProvider localeProvider) {
        this.localeProvider = localeProvider;
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        double executionTime = (System.currentTimeMillis() - start) / 1000;
        localeProvider.getLocalizedString("testing.timer", String.format("%.2f", executionTime));
        return proceed;
    }
}
