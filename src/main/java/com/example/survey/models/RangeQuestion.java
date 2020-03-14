package com.example.survey.models;

import javax.persistence.Entity;

@Entity
public class RangeQuestion extends Question {
    private int min, max;

    public RangeQuestion() {
        super();
    }

    public RangeQuestion(String question, int min, int max) {
        super(question);
        this.max = max;
        this.min = min;
    }

    @Override
    public String toString() {
        return question + " min " + min + " max " + max + ": " + answers.toString();
    }
}
