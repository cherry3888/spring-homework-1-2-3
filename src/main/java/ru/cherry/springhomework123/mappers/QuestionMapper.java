package ru.cherry.springhomework123.mappers;

import org.springframework.stereotype.Component;
import ru.cherry.springhomework123.domain.Question;

public interface QuestionMapper {
    Question getQuestion(String line);
}
