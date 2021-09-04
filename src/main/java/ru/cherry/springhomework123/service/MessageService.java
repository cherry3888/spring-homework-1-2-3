package ru.cherry.springhomework123.service;

public interface MessageService {
    void sendMessage(String message);
    void sendLocalizedMessage(String code, Object... args);
}
