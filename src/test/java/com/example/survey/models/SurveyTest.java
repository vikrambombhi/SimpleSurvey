package com.example.survey.models;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SurveyTest {

    Survey survey;

    @Before
    public void BeforeEachTest() {
        survey = new Survey("Food");
    }

    @Test
    public void addOptionQuestion() {
        assertEquals(0, survey.getOptionQuestions.size());

        OptionQuestion question = new OptionQuestion("What is your favourite type of pizza?");
        survey.addOptionQuestion(question);

        assertEquals(1, survey.getOptionQuestions.size());
        assertEquals(question.getQuestion(), survey.getOptionQuestions.get(0).getQuestion());
    }
}
