package com.example.survey;

import com.example.survey.models.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SurveyApplication {

  public static void main(String[] args) {
    SpringApplication.run(SurveyApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(SurveyRepo repository) {
    return (args) -> {
      Survey survey = new Survey("Test Survey");
      survey.addQuestion(new TextQuestion("Text Question 1"));
      survey.addQuestion(new TextQuestion("Text Question 2"));
      survey.addQuestion(new RangeQuestion("Range Question 1", 1, 100));
      survey.addQuestion(new RangeQuestion("Range Question 2", 2, 200));
      survey.addQuestion(new OptionQuestion("Option Question 1"));
      survey.addQuestion(new OptionQuestion("Option Question 2"));

      repository.save(survey);

      System.out.println("Surveys found with findAll():");
      System.out.println("-------------------------------");
      for (Survey s : repository.findAll()) {
        System.out.println("================");
        System.out.println(s.getName());
        System.out.println("---Questions---");
        for (Question q : s.getQuestions()) {
          System.out.println(q.toString());
        }
      }
    };
  }

}
