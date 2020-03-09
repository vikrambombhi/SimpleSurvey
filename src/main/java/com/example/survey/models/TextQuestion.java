package com.example.survey.models;

import javax.persistence.*;

@Entity
public class TextQuestion extends Question {
    private String answer;

    public TextQuestion() {
    }

    public TextQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(String q) {
        question = q;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return question + ": " + answer;
    }
}
