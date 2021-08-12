package ru.cherry.springhomework123.service;

import org.springframework.stereotype.Service;
import ru.cherry.springhomework123.dao.QuestionDao;
import ru.cherry.springhomework123.domain.Person;
import ru.cherry.springhomework123.domain.Question;

import java.util.List;
import java.util.Scanner;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    private final MessageService messageService;
    private final QuestionDao questionDao;
    private Person person;
    private int result;

    public QuestionnaireServiceImpl(MessageService messageService, QuestionDao questionDao) {
        this.messageService = messageService;
        this.questionDao = questionDao;
    }

    @Override
    public void runQuiz() {
        beginQuiz();
        List<Question> questionList = questionDao.getQuestions();
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            printQuestionAndAnswers(question, i);
            getPersonAnswer(question);
        }
        printQuizResult();
    }

    private void beginQuiz() {
        messageService.sendLocalizedMessage("questionnaire.message.hello");
        messageService.sendLocalizedMessage("questionnaire.message.name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        messageService.sendLocalizedMessage("questionnaire.message.lastname");
        String lastname = scanner.nextLine();
        person = new Person(name, lastname);
        messageService.sendLocalizedMessage("questionnaire.message.start", person.getName(), person.getLastname());
    }

    private void printQuestionAndAnswers(Question question, int i) {
        messageService.sendMessage("");
        messageService.sendLocalizedMessage("questionnaire.message.question", i + 1, question.getContent());
        for(int k = 0; k < question.getAnswers().size(); k++) {
            int answerNumber = k + 1;
            messageService.sendLocalizedMessage("questionnaire.message.answer", answerNumber, question.getAnswers().get(k));
        }
    }

    private void getPersonAnswer(Question question) {
        messageService.sendLocalizedMessage("questionnaire.message.answertoquestion");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt() && scanner.nextInt() == question.getRightAnswerNumber()) {
            result = result + 1;
            messageService.sendLocalizedMessage("questionnaire.message.rightanswer");
        } else {
            messageService.sendLocalizedMessage("questionnaire.message.wronganswer");
        }
    }

    private void printQuizResult() {
        messageService.sendMessage("");
        messageService.sendMessage("*************************************");
        messageService.sendMessage(person.getName() + " " + person.getLastname());
        messageService.sendLocalizedMessage("questionnaire.message.result", result);
    }

}
