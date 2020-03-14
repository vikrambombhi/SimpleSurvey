package com.example.survey.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TextQuestion extends Question {
    private Set<String> answers;

    public TextQuestion() {
        answers = new HashSet();
    }

    public TextQuestion(String question) {
        this.question = question;
        answers = new HashSet();
    }

    public void addAnswer(String answer) {
        answers.add(answer);
    }

    @Override
    public String toString() {
        return question + ": " + answers;
    }

    @Override
    public Set<String> getAnswers() {
        return answers;
    }
}
