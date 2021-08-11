package ru.cherry.springhomework123;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.cherry.springhomework123.dao.QuestionDao;
import ru.cherry.springhomework123.domain.Question;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    QuestionDao questionDao;

    @Test
    void questionDaoTest() {
        List<Question> questionList = questionDao.getQuestions();
        assertNotNull(questionList);
        assertEquals(5, questionList.size());
    }

}
