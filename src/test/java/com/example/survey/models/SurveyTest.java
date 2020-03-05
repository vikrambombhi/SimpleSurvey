package com.example.survey.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SurveyTest {
    public Survey survey;

    @BeforeEach
    public void BeforeEachTest() {
        survey = new Survey("Food");
        survey.setOptionQuestions(new HashSet<OptionQuestion>());
        survey.setRangeQuestions(new HashSet<RangeQuestion>());
        survey.setTextQuestions(new HashSet<TextQuestion>());
    }

    @Test
    public void addOptionQuestion() {
        assertEquals(0, survey.getOptionQuestions().size());

        OptionQuestion question = new OptionQuestion("What is your favourite type of pizza?");
        survey.addOptionQuestion(question);

        assertEquals(1, survey.getOptionQuestions().size());
        assertTrue(survey.getOptionQuestions().contains(question));
    }

    @Test
    public void addRangeQuestion() {
        assertEquals(0, survey.getRangeQuestions().size());

        RangeQuestion question = new RangeQuestion("On a scale of 1 to 10, how much do you like hawaiian pizza?", 1, 10);
        survey.addRangeQuestion(question);

        assertEquals(1, survey.getRangeQuestions().size());
        assertTrue(survey.getRangeQuestions().contains(question));
    }

    @Test
    public void addTextQuestion() {
        assertEquals(0, survey.getTextQuestions().size());

        TextQuestion question = new TextQuestion("What is your favourite type of pizza?");
        survey.addTextQuestion(question);

        assertEquals(1, survey.getTextQuestions().size());
        assertTrue(survey.getTextQuestions().contains(question));
    }
}
