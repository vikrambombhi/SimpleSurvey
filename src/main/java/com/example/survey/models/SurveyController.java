package com.example.survey.models;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class SurveyController {
    private SurveyRepo surveyRepo;

    public SurveyController(SurveyRepo surveyRepo) {
        this.surveyRepo = surveyRepo;
    }

    @GetMapping("/survey")
    public String survey(@RequestParam("id") long id, Model model) {
        Optional<Survey> surveyRepo = this.surveyRepo.findById(id);
        model.addAttribute("survey", surveyRepo.get());
        return "survey";
    }

    @PostMapping("/survey")
    @ResponseBody
    public Survey getSurvey(@RequestParam("id") long id) {
        Optional<Survey> surveyRepo = this.surveyRepo.findById(id);
        return surveyRepo.get();
    }

    @PostMapping("/new")
    @ResponseBody
    public Survey newSurvey(@RequestParam("name") String name) {
        Survey s = new Survey(name);
        this.surveyRepo.save(s);
        return s;
    }

    @PostMapping("/close")
    @ResponseBody
    public Survey closeSurvey(@RequestParam("id") long id) {
        Optional<Survey> s = this.surveyRepo.findById(id);
        s.get().setClosed(true);
        this.surveyRepo.save(s.get());
        return s.get();
    }
}
