package com.example.survey.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

class OptionQuestionTest {

    @Test
    void getQuestion() {
        OptionQuestion oq = new OptionQuestion("foo");
        assertEquals("foo", oq.getQuestion());
    }

    @Test
    void getOptions() {
        HashSet<String> ops = new HashSet();
        ops.add("bar");

        OptionQuestion oq = new OptionQuestion("", ops);
        assertNotNull(oq.getOptions());
        assertTrue(oq.getOptions().contains("bar"));
    }

    @Test
    void setOptions() {
        HashSet<String> ops = new HashSet();
        ops.add("bar");

        OptionQuestion oq = new OptionQuestion();
        oq.setOptions(ops);
        assertNotNull(oq.getOptions());
        assertTrue(oq.getOptions().contains("bar"));
    }

    @Test
    void addOption() {
        OptionQuestion oq = new OptionQuestion("");
        oq.addOption("bar");
        assertNotNull(oq.getOptions());
        assertTrue(oq.getOptions().contains("bar"));
    }

    @Test
    void testToString() {
        OptionQuestion oq = new OptionQuestion("who's joe?");
        oq.addOption("biden");
        oq.addOption("mama");

        oq.addAnswer(new Answer("mama"));

        assertThat(oq.toString(), containsString("[mama]"));
        assertThat(oq.toString(), containsString("[biden, mama]"));
    }
}