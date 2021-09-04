package ru.cherry.springhomework123.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class Config {

    @Bean
    Locale getLocale(@Value("${spring.web.locale}") Locale locale) {
        return locale;
    }

}
