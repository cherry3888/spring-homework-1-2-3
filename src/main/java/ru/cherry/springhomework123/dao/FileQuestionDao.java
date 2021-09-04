package ru.cherry.springhomework123.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.cherry.springhomework123.domain.Question;
import ru.cherry.springhomework123.mappers.QuestionMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.Objects.requireNonNull;

@Component
public class FileQuestionDao implements QuestionDao{
    private static final Logger LOG = LoggerFactory.getLogger(FileQuestionDao.class);
    private static final String FILENAME_LOCALE_DELIMETER = "_";
    private static final String FILE_EXTENSION = ".csv";
    private final String fileName;
    private final QuestionMapper questionMapper;

    public FileQuestionDao(@Value("${questionnaire.filename}") String fileName, Locale locale, QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
        this.fileName = fileName + FILENAME_LOCALE_DELIMETER + locale + FILE_EXTENSION;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(requireNonNull(this.getClass().getClassLoader().getResourceAsStream(fileName)), StandardCharsets.UTF_8))){
            String oneLine;
            while ((oneLine = bufferedReader.readLine()) != null) {
                Question question = questionMapper.getQuestion(oneLine);
                questionList.add(question);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return questionList;
    }

}
