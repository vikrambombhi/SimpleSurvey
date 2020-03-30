package com.example.survey.controllers;

import com.example.survey.models.Survey;
import com.example.survey.models.SurveyRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/api")
public class SurveyController {
    private SurveyRepo surveyRepo;

    public SurveyController(SurveyRepo surveyRepo) {
        this.surveyRepo = surveyRepo;
    }

    @GetMapping("/surveys")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "getSurveysFallback")
    public Iterable<Survey> getSurveys() {
        return this.surveyRepo.findAll();
    }

    public Iterable<Survey> getSurveysFallback() {
        return new ArrayList<Survey>();
    }

    @GetMapping("/survey")
    @ResponseBody
    public Survey getSurvey(@RequestParam("id") long id) {
        Survey survey = this.surveyRepo.findById(id);
        return survey;
    }

    @PostMapping(value = "/new", produces = "application/json; charset=utf-8", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @HystrixCommand(commandKey = "newSurvey")
    public Survey newSurvey(@RequestBody Survey survey) {
        this.surveyRepo.save(survey);
        return survey;
    }

    @PostMapping("/close")
    @ResponseBody
    public Survey closeSurvey(@RequestParam("id") long id) {
        Survey s = this.surveyRepo.findById(id);
        s.setClosed(true);
        this.surveyRepo.save(s);
        return s;
    }
}
