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

    @PostMapping(value = "/answers", produces = "application/json; charset=utf-8", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void answerQuestions(@RequestBody List<Question>questions, @RequestBody List<Answer> answers) {
        if (answers.size() != questions.size()) {
            return;
        }

        for (int i = 0; i < questions.size(); i++) {
            Question q = this.questionRepo.findById(questions.get(i).getId());
            q.addAnswer(answers.get(i));
            this.questionRepo.save(q);
        }
    }
}