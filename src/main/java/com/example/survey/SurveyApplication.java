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

      Set<TextQuestion> textQuestions = new HashSet<>();
      textQuestions.add(new TextQuestion("Text Question 1"));
      textQuestions.add(new TextQuestion("Text Question 2"));

      Set<OptionQuestion> optionQuestions = new HashSet<>();
      optionQuestions.add(new OptionQuestion("Option Question 1"));
      optionQuestions.add(new OptionQuestion("Option Question 2"));

      Set<RangeQuestion> rangeQuestions = new HashSet<>();
      rangeQuestions.add(new RangeQuestion("Range Question 1", 1, 100));
      rangeQuestions.add(new RangeQuestion("Range Question 2", 2, 200));

      Survey survey = new Survey("Test Survey");
      survey.setTextQuestions(textQuestions);
      survey.setOptionQuestions(optionQuestions);
      survey.setRangeQuestions(rangeQuestions);


      repository.save(survey);

      System.out.println("Surveys found with findAll():");
      System.out.println("-------------------------------");
      for (Survey s : repository.findAll()) {
        System.out.println("================");
        System.out.println(s.name);
        System.out.println("---Text Questions---");
        for (TextQuestion t : s.getTextQuestions()) {
          System.out.println(t.toString());
        }
        System.out.println("---Option Questions---");
        for (OptionQuestion o : s.getOptionQuestions()) {
          System.out.println(o.toString());
        }
        System.out.println("---Range Questions---");
        for (RangeQuestion r : s.getRangeQuestions()) {
          System.out.println(r.toString());
        }
      }

    };
  }

}
