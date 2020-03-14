package com.example.survey.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String question;

    public abstract Set<String> getAnswers();

    public abstract void addAnswer(String ans);

    public void setQuestion(String q) {
        question = q;
    }

    public String getQuestion() {
        return question;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public boolean equals(Object o) {
        if (o instanceof Question) {
            Question q = (Question) o;
            return (q.id == id);
        }
        return false;
    }
}
