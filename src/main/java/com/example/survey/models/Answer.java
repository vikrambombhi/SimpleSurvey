package com.example.survey.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    int val;
    String response;

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
        response = "";
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

    public String toString() {
        if (response == null) {
            return "" + val;
        }
        return response;
    }
}
