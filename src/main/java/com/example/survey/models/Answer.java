package com.example.survey.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Answer implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    int val;
    String response;
    @ManyToOne(fetch = FetchType.EAGER)
    Question question;

    public Answer() {

    }

    public Answer(int v) {
        val = v;
    }

    public Answer(String resp) {
        response = resp;
    }

    public void setVal(int v) {
        val = v;
        response = null;
    }

    public int getVal() {
        return val;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String resp) {
        response = resp;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String toString() {
        if (response == null) {
            return "" + val;
        }
        return response;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Answer) {
            Answer a = (Answer) o;
            if (response == null) {
                return val - a.getVal();
            }
            return response.compareTo(a.getResponse());
        }
        return 0;
    }
}
