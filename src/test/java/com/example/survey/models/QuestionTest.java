package com.example.survey.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

class QuestionTest {

    Question question;
    @BeforeEach
    public void BeforeEachTest() {
         question = new TextQuestion("What is your name?");
    }

    @Test
    void testEquals() {
        question.id = 3;
        TextQuestion question2 = new TextQuestion();
        question2.setId(2);
        assertFalse(question.equals(question2));
        question2.setId(3);
        assertTrue(question.equals(question2));
    }

    @Test
    void testTextQuestionJsonSerialization() {
        question.id = 3;
        question.addAnswer(new Answer("Joe"));
        question.addAnswer(new Answer("Jim"));
        try {
            String result = new ObjectMapper().writeValueAsString(question);
            assertThat(result, containsString("\"question\":\"What is your name?\""));
            assertThat(result, containsString("\"response\":\"Joe\""));
            assertThat(result, containsString("\"response\":\"Jim\""));
        } catch (JsonProcessingException jpe) {
            assertNull(jpe);
        }
    }

    @Test
    void testOptionQuestionJsonSerialization() {
        OptionQuestion question = new OptionQuestion("What is your favourite colour?");
        question.addOption("blue");
        question.addOption("green");
        question.addAnswer(new Answer("blue"));
        question.id = 3;
        try {
            String expected = "{\"type\":\"option\",\"options\":[\"green\",\"blue\"],\"id\":3,\"question\":\"What is your favourite colour?\",\"answers\":[{\"val\":0,\"response\":\"blue\"}]}";
            String result = new ObjectMapper().writeValueAsString(question);
            assertEquals(expected, result);
        } catch (JsonProcessingException jpe) {
            assertNull(jpe);
        }
    }

    @Test
    void testRangeQuestionJsonSerialization() {
        RangeQuestion question = new RangeQuestion("What is your birthyear?", 1995, 2020);
        question.id = 3;
        question.addAnswer(new Answer(1997));
        question.addAnswer(new Answer(2001));
        try {
            String result = new ObjectMapper().writeValueAsString(question);
            assertThat(result, containsString("\"val\":1997"));
        } catch (JsonProcessingException jpe) {
            assertNull(jpe);
        }
    }
}