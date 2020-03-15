package com.example.survey.models;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;

@Entity
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

    public int getMin() { return min; }
    public int getMax() { return max; }
    public void setMin(int m) { min = m; }
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
        ArrayList<Answer> sorted = new ArrayList<Answer>(answers);
        Collections.sort(sorted);
        return question + " min " + min + " max " + max + ": " + sorted.toString();
    }
}
