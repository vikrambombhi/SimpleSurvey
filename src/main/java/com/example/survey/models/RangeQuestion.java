package com.example.survey.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;

@Entity
@JsonTypeName("range")
public class RangeQuestion extends Question {
    private int min, max;

    public RangeQuestion() {
        super();
    }

    public RangeQuestion(String question, int min, int max) {
        super(question);
        this.max = max;
        this.min = min;
    }

    @JsonGetter("min")
    public int getMin() { return min; }
    @JsonGetter("max")
    public int getMax() { return max; }
    @JsonSetter("min")
    public void setMin(int m) { min = m; }
    @JsonSetter("max)")
    public void setMax(int m) { max = m; }

    @Override
    public void addAnswer(Answer ans) {
        if (ans.getVal() <= max && ans.getVal() >= min) {
            super.addAnswer(ans);
            return;
        }
    }

    @Override
    public String toString() {
        return question + " min " + min + " max " + max + ": " + answers.toString();
    }
}
