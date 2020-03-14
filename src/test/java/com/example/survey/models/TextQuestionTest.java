package com.example.survey.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TextQuestionTest {
    public TextQuestion question;
    public String q;

    @BeforeEach
    public void BeforeEachTest() {
        q = "What is your favourite type of pizza?";
        question = new TextQuestion(q);
    }

    @Test
    public void addAnswer() {
        Answer answer = new Answer("pepperoni");
        String expected = q + ": " + "[" + answer + "]";
        question.addAnswer(answer);

        assertEquals(expected, question.toString());
    }
}
