package com.example.survey.controllers;

import com.example.survey.models.Answer;
import com.example.survey.models.Question;
import com.example.survey.models.QuestionRepo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class QuestionController {
    private QuestionRepo questionRepo;

    public QuestionController(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @PostMapping(value = "/answers", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Answer> answerQuestions(@RequestBody List<Answer> answers) {
        for (Answer a: answers) {
            Question q = this.questionRepo.findById(a.getQuestion().getId());
            q.addAnswer(a);
            questionRepo.save(q);
        }
        return answers;
    }
}
