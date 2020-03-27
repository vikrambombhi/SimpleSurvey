package com.example.survey.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SurveyTest {
    public Survey survey;

    @BeforeEach
    public void BeforeEachTest() {
        survey = new Survey("Food");
    }

    @Test
    public void addOptionQuestion() {
        assertEquals(0, survey.getQuestions().size());

        OptionQuestion question = new OptionQuestion("What is your favourite type of pizza?");
        survey.addQuestion(question);

        assertEquals(1, survey.getQuestions().size());
        assertTrue(survey.getQuestions().contains(question));
    }

    @Test
    public void addRangeQuestion() {
        assertEquals(0, survey.getQuestions().size());

        RangeQuestion question = new RangeQuestion("On a scale of 1 to 10, how much do you like hawaiian pizza?", 1, 10);
        survey.addQuestion(question);

        assertEquals(1, survey.getQuestions().size());
        assertTrue(survey.getQuestions().contains(question));
    }

    @Test
    public void addTextQuestion() {
        assertEquals(0, survey.getQuestions().size());

        TextQuestion question = new TextQuestion("What is your favourite type of pizza?");
        survey.addQuestion(question);

        assertEquals(1, survey.getQuestions().size());
        assertTrue(survey.getQuestions().contains(question));
    }
}
