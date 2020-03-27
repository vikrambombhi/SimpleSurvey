package com.example.survey.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OptionQuestion.class, name = "option"),
        @JsonSubTypes.Type(value = RangeQuestion.class, name = "range"),
        @JsonSubTypes.Type(value = TextQuestion.class, name = "text")
})
public abstract class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    protected String question;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    protected Set<Answer> answers;

    public Question() {
        answers = new HashSet();
    }

    public Question(String ques) {
        question = ques;
        answers = new HashSet();
    }

    @JsonGetter("answers")
    public Set<Answer> getAnswers() {
        return answers;
    }

    @JsonSetter("answers")
    public void setAnswers(Set<Answer> ans) {
        answers = ans;
    }

    public void addAnswer(Answer ans) {
        answers.add(ans);
    }

    @JsonSetter("question")
    public void setQuestion(String q) {
        question = q;
    }

    @JsonGetter("question")
    public String getQuestion() {
        return question;
    }

    @JsonSetter("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonGetter("id")
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
