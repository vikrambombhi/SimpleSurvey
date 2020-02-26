package com.example.survey.models;

import javax.persistence.*;

@Entity
public class TextQuestion {
    private String question;
    private String answer;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public TextQuestion() {}
    public TextQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

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
