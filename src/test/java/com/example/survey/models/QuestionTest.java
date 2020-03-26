package com.example.survey.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testTextQuestionJsonDeserialization() {
        String json = "{\"type\":\"text\",\"question\":\"What is your name?\"}";
        ObjectMapper om = new ObjectMapper();
        TextQuestion tq;
        try {
            tq = om.readValue(json, TextQuestion.class);
            assertEquals(new TextQuestion("What is your name?"), tq);
        } catch (JsonProcessingException jpe) {
            assertNull(jpe);
        }
    }

    @Test
    void testOptionQuestionJsonDeserialization() {
        String json = "{\"type\":\"option\",\"question\":\"DNC?\",\"options\":[\"biden\",\"bernie\"]}";
        ObjectMapper om = new ObjectMapper();
        OptionQuestion oq;
        try {
            oq = om.readValue(json, OptionQuestion.class);
            OptionQuestion expected = new OptionQuestion("DNC?");
            expected.addOption("biden");
            expected.addOption("bernie");
            assertEquals(expected, oq);
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
            String expected = "{\"type\":\"option\",\"options\":[\"blue\",\"green\"],\"id\":3,\"question\":\"What is your favourite colour?\",\"answers\":[{\"val\":0,\"response\":\"blue\"}]}";
            String result = new ObjectMapper().writeValueAsString(question);
            assertThat(result, containsString("\"question\":\"What is your favourite colour?\""));
            assertThat(result, containsString("green"));
            assertThat(result, containsString("\"response\":\"blue\""));
        } catch (JsonProcessingException jpe) {
            assertNull(jpe);
        }
    }

    @Test
    void testRangeQuestionJsonDeserialization() {
        RangeQuestion question = new RangeQuestion("What is your birthyear?", 1995, 2020);
        question.id = 3;
        question.addAnswer(new Answer(1997));
        question.addAnswer(new Answer(2001));

        ObjectMapper om = new ObjectMapper();
        String json = "{\"type\":\"range\",\"question\":\"What is your birthyear?\",\"min\":\"1995\",\"max\":\"2020\"}";
        RangeQuestion rq;
        try {
            rq = om.readValue(json, RangeQuestion.class);
            RangeQuestion expected = new RangeQuestion("What is your birthyear?", 1995, 2020);
            assertEquals(expected, rq);
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