package com.example.survey.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonTypeName("option")
public class OptionQuestion extends Question {
    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private Set<String> options;

    public OptionQuestion() {
        super();
        options = new HashSet();
    }

    public OptionQuestion(String question) {
        super(question);
        options = new HashSet();
    }

    public OptionQuestion(String question, Set<String> options) {
        super(question);
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

    @Override
    public String toString() {
        return question + " " + options.toString() + ": " + answers.toString();
    }
}
