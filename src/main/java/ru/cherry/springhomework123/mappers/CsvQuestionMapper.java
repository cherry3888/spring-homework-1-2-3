package ru.cherry.springhomework123.mappers;

import org.springframework.stereotype.Component;
import ru.cherry.springhomework123.domain.Question;

import java.util.Arrays;

@Component
public class CsvQuestionMapper implements QuestionMapper {
    @Override
    public Question getQuestion(String line) {
        String[] lineArr = line.split(";");
        return new Question(lineArr[0], Integer.valueOf(lineArr[1]), Arrays.asList(Arrays.copyOfRange(lineArr, 2, lineArr.length)));
    }
}
