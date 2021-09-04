package ru.cherry.springhomework123.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ConsoleMessageService implements MessageService{
    private final MessageSource messageSource;
    private final Locale locale;

    public ConsoleMessageService(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void sendLocalizedMessage(String message, Object... args) {
        System.out.println(getLocalizedMessage(message, args));
    }

    private String getLocalizedMessage(String messageCode, Object... args) {
        return messageSource.getMessage(messageCode, args, locale);
    }
}
