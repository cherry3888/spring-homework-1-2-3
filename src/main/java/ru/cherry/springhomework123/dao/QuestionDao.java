package ru.cherry.springhomework123.dao;

import ru.cherry.springhomework123.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}
