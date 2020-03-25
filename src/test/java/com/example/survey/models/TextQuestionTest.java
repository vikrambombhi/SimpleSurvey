package com.example.survey.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
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

        assertThat(question.toString(), containsString(answer.toString()));
    }
}
