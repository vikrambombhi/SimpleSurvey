package com.example.survey.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RangeQuestion extends Question {
    private int min, max;
    @ElementCollection(targetClass=Integer.class, fetch = FetchType.EAGER)
    private Set<Answer> answers;

    public RangeQuestion() {
        answers = new HashSet();
    }

    public RangeQuestion(String question, int min, int max) {
        this.question = question;
        this.max = max;
        this.min = min;
        answers = new HashSet();
    }

    @Override
    public String toString() {
        return question + " min " + min + " max " + max + ": " + answers.toString();
    }

    @Override
    public Set<Answer> getAnswers() {
        return answers;
    }

    @Override
    public void addAnswer(Answer ans) {
        answers.add(ans);
    }
}
