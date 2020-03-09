package com.example.survey.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private boolean closed;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public Set<Question> questions;

    public Survey() {
        questions = new HashSet<>();
    }

    public Survey(String name) {
        this.name = name;
        this.closed = false;
        questions = new HashSet<>();
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isClosed() {
        return closed;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> qs) {
        questions = qs;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

}
