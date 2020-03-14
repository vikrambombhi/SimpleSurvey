package com.example.survey.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TextQuestion extends Question {
    @ElementCollection(targetClass=String.class, fetch = FetchType.EAGER)
    private Set<Answer> answers;

    public TextQuestion() {
        answers = new HashSet();
    }

    public TextQuestion(String question) {
        this.question = question;
        answers = new HashSet();
    }

    @Override
    public String toString() {
        return question + ": " + answers.toString();
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
