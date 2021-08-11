package ru.cherry.springhomework123.service;

import org.springframework.stereotype.Service;
import ru.cherry.springhomework123.dao.QuestionDao;
import ru.cherry.springhomework123.domain.Person;
import ru.cherry.springhomework123.domain.Question;

import java.util.List;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final MessageService messageService;
    private final QuestionDao questionDao;

    public QuestionnaireServiceImpl(MessageService messageService, QuestionDao questionDao) {
        this.messageService = messageService;
        this.questionDao = questionDao;
    }

    @Override
    public void runQuiz() {
        int result = 0;
        Person person = new Person();
        messageService.sendLocalizedMessage("questionnaire.message.hello");
        messageService.sendLocalizedMessage("questionnaire.message.name");
        Scanner scanner = new Scanner(System.in);
        person.setName(scanner.nextLine());
        messageService.sendLocalizedMessage("questionnaire.message.lastname");
        person.setLastname(scanner.nextLine());
        messageService.sendLocalizedMessage("questionnaire.message.start", person.getName(), person.getLastname());

        List<Question> questionList = questionDao.getQuestions();

        for (int i = 0; i < questionList.size(); i++) {
            messageService.sendMessage("");
            int questionNumber = i + 1;
            Question question = questionList.get(i);
            messageService.sendLocalizedMessage("questionnaire.message.question", questionNumber, questionList.get(i).getContent());
            for(int k = 0; k < question.getAnswers().size(); k++) {
                int answerNumber = k + 1;
                messageService.sendLocalizedMessage("questionnaire.message.answer", answerNumber, question.getAnswers().get(k));
            }
            messageService.sendLocalizedMessage("questionnaire.message.answertoquestion");
            if (scanner.hasNextInt() && scanner.nextInt() == question.getRightAnswerNumber()) {
                result = result + 1;
                messageService.sendLocalizedMessage("questionnaire.message.rightanswer");
            } else {
                messageService.sendLocalizedMessage("questionnaire.message.wronganswer");
            }
        }
        messageService.sendMessage("");
        messageService.sendMessage("*************************************");
        messageService.sendLocalizedMessage("questionnaire.message.result", result);
    }
}
