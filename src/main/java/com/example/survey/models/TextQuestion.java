package com.example.survey.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.*;

@Entity
@JsonTypeName("text")
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
