package com.example.survey.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RangeQuestion extends Question {
    private int min, max;
    private Set<Integer> answers;

    public RangeQuestion() {
        answers = new HashSet();
    }

    public RangeQuestion(String question, int min, int max) {
        this.question = question;
        this.max = max;
        this.min = min;
        answers = new HashSet();
    }

    public void addAnswer(int answer) {
        answers.add(answer);
    }

    @Override
    public String toString() {
        return question + " min " + min + " max " + max + ": " + answers;
    }

    @Override
    public Set<String> getAnswers() {
        HashSet<String> toret = new HashSet();
        for (int ans : answers) {
            toret.add("" + ans);
        }
        return toret;
    }

    @Override
    public void addAnswer(String ans) {
        int response;
        try {
            response = Integer.parseInt(ans);
        } catch (NumberFormatException e) {
            response = 0;
        }
        addAnswer(response);
    }
}
