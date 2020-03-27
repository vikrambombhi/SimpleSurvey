package com.example.survey.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswerTest {

    private Answer valAnswer, strAnswer;

    @BeforeEach
    public void setupTests() {
        valAnswer = new Answer(1);
        strAnswer = new Answer("UwU");
    }

    @Test
    public void testSetGetVal() {
        int expected = 2;
        valAnswer.setVal(expected);
        assertEquals(expected, valAnswer.getVal());
    }

    @Test
    public void testSetGetResponse() {
        String expected = "raccoon";
        strAnswer.setResponse(expected);
        assertEquals(expected, strAnswer.getResponse());
    }

    @Test
    public void testToString() {
        assertEquals("1", valAnswer.toString());
        assertEquals("UwU", strAnswer.toString());
    }

    @Test
    public void compareTo() {
        valAnswer.setVal(1);
        Answer valAnswer2 = new Answer(2);
        assertEquals(-1, valAnswer.compareTo(valAnswer2));

        strAnswer.setResponse("hello");
        Answer strAnswer2 = new Answer("hi");
        assertEquals(-4, strAnswer.compareTo(strAnswer2));
    }

}