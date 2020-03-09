package com.example.survey.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RangeQuestion extends Question {
    private int min, max, answer;

    public RangeQuestion() {
    }

    public RangeQuestion(String question, int min, int max) {
        this.question = question;
        this.max = max;
        this.min = min;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(String q) {
        question = q;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return question + " min " + min + " max " + max + ": " + answer;
    }

    @Override
    public String getAnswer() {
        return "" + answer;
    }
}
