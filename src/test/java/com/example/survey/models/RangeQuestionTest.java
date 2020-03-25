package com.example.survey.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

class RangeQuestionTest {

    @Test
    void getQuestion() {
        String expected = "how much does kirin bench (in lbs)?";
        RangeQuestion rq = new RangeQuestion(expected, 45, 205);
        assertEquals(expected, rq.getQuestion());
    }

    @Test
    void testToString() {
        RangeQuestion rq = new RangeQuestion("how much does kirin bench (in lbs)?", 45, 205);
        rq.addAnswer(new Answer(45));
        String expected = "how much does kirin bench (in lbs)? min 45 max 205: [45]";
        assertEquals(expected, rq.toString());
    }

    @Test
    void settersAndGetters() {
        RangeQuestion rq = new RangeQuestion();
        int minExpect = 1, maxExpect = 3;
        rq.setMin(minExpect);
        rq.setMax(maxExpect);
        assertEquals(minExpect, rq.getMin());
        assertEquals(maxExpect, rq.getMax());
    }

    @Test
    void addAnswerOverride() {
        RangeQuestion rq = new RangeQuestion("vals", 1, 3);
        rq.addAnswer(new Answer(0));
        rq.addAnswer(new Answer(1));
        rq.addAnswer(new Answer(2));
        rq.addAnswer(new Answer(3));
        rq.addAnswer(new Answer(4));

        ArrayList<Answer> sortedAnswers = new ArrayList(rq.getAnswers());
        Collections.sort(sortedAnswers);

        String expected = "[1, 2, 3]";
        assertEquals(expected, sortedAnswers.toString());
    }
}