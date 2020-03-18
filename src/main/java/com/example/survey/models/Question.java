package com.example.survey.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String question;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    protected Set<Answer> answers;

    public Question() {
        answers = new HashSet();
    }

    public Question(String ques) {
        question = ques;
        answers = new HashSet();
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer ans) {
        answers.add(ans);
    }

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
