package com.example.survey.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeQuestionTest {

    @Test
    void getQuestion() {
        String expected =  "how much does kirin bench (in lsb)?";
        RangeQuestion rq = new RangeQuestion(expected, 45, 240);
        assertTrue(expected.equals(rq.getQuestion()));
    }

    @Test
    void testToString() {
        RangeQuestion rq = new RangeQuestion("how much does kirin bench (in lsb)?", 45, 240);
        rq.setAnswer(45);
        String expected = "how much does kirin bench (in lsb)? min 45 max 240: 45";
        assertTrue(expected.equals(rq.toString()));
    }
}