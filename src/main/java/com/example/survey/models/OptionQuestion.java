package com.example.survey.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OptionQuestion extends Question {
    private String answer;
    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private Set<String> options;

    public OptionQuestion() {
        options = new HashSet<String>();
    }

    public OptionQuestion(String question) {
        this.question = question;
        options = new HashSet<String>();
    }

    public OptionQuestion(String question, Set<String> options) {
        this.question = question;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public void setQuestion(String q) {
        question = q;
    }

    public Set<String> getOptions() {
        return options;
    }

    public void setOptions(Set<String> options) {
        this.options = options;
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        String options = " options (";
        options += String.join(", ", this.options);
        return question + options + "): " + answer;
    }

}
