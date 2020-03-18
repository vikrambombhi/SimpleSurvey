package com.example.survey.models;

import javax.persistence.*;

@Entity
public class TextQuestion extends Question {

    public TextQuestion() {
       super();
    }

    public TextQuestion(String question) {
        super(question);
    }

    @Override
    public String toString() {
        return question + ": " + answers.toString();
    }
}
