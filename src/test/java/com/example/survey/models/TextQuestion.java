package com.example.survey.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TextQuestion {
    public TextQuestion question;
    public String q;

    @BeforeEach
    public void BeforeEachTest() {
        q = "What is your favourite type of pizza?";
        question = new TextQuestion(q);
    }

    @Test
    public void toString() {
        String answer = "pepperoni";
        question.setAnswer("Pepperoni");

        assertEquals(q + ": " + answer, question.toString());
    }
}
