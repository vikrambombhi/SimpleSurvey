package com.example.survey.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RangeQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String question;
    private Integer min;
    private Integer max;
    private Integer answer;

    public RangeQuestion() {}

    public RangeQuestion(String question, Integer min, Integer max) {
        this.question = question;
        this.max = max;
        this.min = min;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return question + " min " + min + " max " + max +": " + answer;
    }
}
