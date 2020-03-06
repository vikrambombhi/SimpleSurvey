package com.example.survey.models;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SurveyController {
    private SurveyRepo surveyRepo;

    public SurveyController(SurveyRepo surveyRepo) {
        this.surveyRepo = surveyRepo;
    }

    @GetMapping("/survey")
    public String getSurvey(@RequestParam("id") Integer id, Model model) {
        Survey surveyRepo = this.surveyRepo.findById(id);
        model.addAttribute("survey", surveyRepo);
        return "survey";
    }
}
