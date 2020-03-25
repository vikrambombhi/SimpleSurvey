package com.example.survey.controllers;
import com.example.survey.models.Question;
import com.example.survey.models.Survey;
import com.example.survey.models.SurveyRepo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/api")
public class SurveyController {
    private SurveyRepo surveyRepo;

    public SurveyController(SurveyRepo surveyRepo) {
        this.surveyRepo = surveyRepo;
    }

    @GetMapping("/survey")
    public String survey(@RequestParam("id") long id, Model model) {
        Survey survey = this.surveyRepo.findById(id);
        model.addAttribute("survey", survey);
        return "survey";
    }

    @PostMapping("/survey")
    @ResponseBody
    public Survey getSurvey(@RequestParam("id") long id) {
        Survey survey = this.surveyRepo.findById(id);
        return survey;
    }

    @PostMapping(value = "/new", produces = "application/json; charset=utf-8", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Survey newSurvey(@RequestBody Survey survey) {
        this.surveyRepo.save(survey);
        for (Question q : survey.questions) {
            System.out.println("" + q.toString());
        }
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
