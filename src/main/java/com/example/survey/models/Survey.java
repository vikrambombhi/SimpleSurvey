package com.example.survey.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public String name;
    public boolean closed;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public Set<TextQuestion> textQuestions;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public Set<OptionQuestion> optionQuestions;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public Set<RangeQuestion> rangeQuestions;

    public Survey() {}

    public Survey(String name) {
        this.name = name;
        this.closed = false;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getName() {
        return name;
    }

    public Set<TextQuestion> getTextQuestions() {
        return textQuestions;
    }

    public void setTextQuestions(Set<TextQuestion> textQuestions) {
        this.textQuestions = textQuestions;
    }

    public void addTextQuestion(TextQuestion question) {
        this.textQuestions.add(question);
    }

    public Set<OptionQuestion> getOptionQuestions() {
        return optionQuestions;
    }

    public void setOptionQuestions(Set<OptionQuestion> optionQuestions) {
        this.optionQuestions = optionQuestions;
    }

    public void addOptionQuestion(OptionQuestion question) {
        this.optionQuestions.add(question);
    }

    public Set<RangeQuestion> getRangeQuestions() {
        return rangeQuestions;
    }

    public void setRangeQuestions(Set<RangeQuestion> rangeQuestions) {
        this.rangeQuestions = rangeQuestions;
    }

    public void addRangeQuestion(RangeQuestion question) {
        this.rangeQuestions.add(question);
    }
}
