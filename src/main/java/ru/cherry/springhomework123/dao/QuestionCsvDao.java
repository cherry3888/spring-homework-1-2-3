package ru.cherry.springhomework123.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework123.domain.Question;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import static java.util.Objects.requireNonNull;

@Repository
public class QuestionCsvDao implements QuestionDao{

    private static final String FILENAME_LOCALE_DELIMETER = "_";
    private static final String FILE_EXTENSION = ".csv";
    private final String fileName;

    public QuestionCsvDao(@Value("${questionnaire.filename}") String fileName, Locale locale) {
        this.fileName = fileName + FILENAME_LOCALE_DELIMETER + locale + FILE_EXTENSION;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(requireNonNull(this.getClass().getClassLoader().getResourceAsStream(fileName)), StandardCharsets.UTF_8))){
            String oneLine;
            while ((oneLine = bufferedReader.readLine()) != null) {
                String[] lineArr = oneLine.split(";");
                Question question = new Question(lineArr[0], Integer.valueOf(lineArr[1]), Arrays.asList(Arrays.copyOfRange(lineArr, 2, lineArr.length)));
                questionList.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionList;
    }
}
