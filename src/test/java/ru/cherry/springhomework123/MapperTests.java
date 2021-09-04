package ru.cherry.springhomework123;

import org.junit.jupiter.api.Test;
import ru.cherry.springhomework123.domain.Question;
import ru.cherry.springhomework123.mappers.CsvQuestionMapper;
import ru.cherry.springhomework123.mappers.QuestionMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class MapperTests {

    @Test
    void questionMapperTest() {
        QuestionMapper questionMapper = new CsvQuestionMapper();
        Question question = questionMapper.getQuestion("Угадайте загаданное число;2;2;8;6;3");

        assertNotNull(question);
        assertEquals("Угадайте загаданное число", question.getContent());
        assertEquals(2, question.getRightAnswerNumber());
        assertNotNull(question.getContent());
        assertEquals("2", question.getAnswers().get(0));
        assertEquals("8", question.getAnswers().get(1));
        assertEquals("6", question.getAnswers().get(2));
        assertEquals("3", question.getAnswers().get(3));
    }

}
