package com.example.survey.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OptionQuestion extends Question {
    @ElementCollection(targetClass=String.class, fetch = FetchType.EAGER)
    private Set<Answer> answers;
    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private Set<String> options;

    public OptionQuestion() {
        options = new HashSet();
        answers = new HashSet();
    }

    public OptionQuestion(String question) {
        this.question = question;
        options = new HashSet();
        answers = new HashSet();
    }

    public OptionQuestion(String question, Set<String> options) {
        this.question = question;
        this.options = options;
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


    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public String toString() {
        String options = " options (";
        options += String.join(", ", this.options);
        return question + options + "): " + answers.toString();
    }

    @Override
    public Set<Answer> getAnswers() {
        return answers;
    }
}
