package com.example.survey.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    Question question;
    @BeforeEach
    public void BeforeEachTest() {
         question = new TextQuestion();
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
}