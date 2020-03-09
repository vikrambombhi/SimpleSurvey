package com.example.survey.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String question;
    public abstract String getQuestion();
    public abstract void setQuestion(String q);
    public abstract String getAnswer(); // setter handled by subclass
    public boolean equals(Object o) {
        if (o instanceof Question) {
            Question q = (Question) o;
            return (q.id == id);
        }
        return false;
    }
}
