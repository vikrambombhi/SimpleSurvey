package com.example.survey.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeQuestionTest {

    @Test
    void getQuestion() {
        String expected =  "how much does kirin bench (in lbs)?";
        RangeQuestion rq = new RangeQuestion(expected, 45, 205);
        assertEquals(expected, rq.getQuestion());
    }

    @Test
    void testToString() {
        RangeQuestion rq = new RangeQuestion("how much does kirin bench (in lbs)?", 45, 205);
        rq.addAnswer(45);
        String expected = "how much does kirin bench (in lbs)? min 45 max 205: [45]";
        assertEquals(expected, rq.toString());
    }
}