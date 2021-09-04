package ru.cherry.springhomework123.domain;

import java.util.List;

public class Question {
    private final String content;
    private final Integer rightAnswerNumber;
    private final List<String> answers;

    public Question(String content, Integer rightAnswerNumber, List<String> answers) {
        this.content = content;
        this.rightAnswerNumber = rightAnswerNumber;
        this.answers = answers;
    }

    public String getContent() {
        return content;
    }

    public Integer getRightAnswerNumber() {
        return rightAnswerNumber;
    }

    public List<String> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "content='" + content + '\'' +
                ", rightAnswerNumber=" + rightAnswerNumber +
                ", answers=" + answers +
                '}';
    }
}
