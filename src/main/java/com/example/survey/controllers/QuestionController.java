package com.example.survey.controllers;

import com.example.survey.models.Answer;
import com.example.survey.models.Question;
import com.example.survey.models.QuestionRepo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public void answerQuestions(@RequestBody List<Question> questions) {
        for (Question reqQuestion: questions) {
            Question question = this.questionRepo.findById(reqQuestion.getId());
            for (Answer a: reqQuestion.getAnswers()) {
                question.addAnswer(a);
            }
            this.questionRepo.save(question);
        }
        return;
    }
}


