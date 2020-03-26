package com.survey.commands;

import com.example.survey.models.OptionQuestion;
import com.example.survey.models.Question;
import com.example.survey.models.Survey;
import com.example.survey.models.SurveyRepo;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class RepoCommand extends HystrixCommand<Survey> {
    private SurveyRepo repo;
    private Survey survey;

    public RepoCommand(SurveyRepo repo, Survey survey) {
        super(HystrixCommandGroupKey.Factory.asKey("SurveyGroup"));
        this.repo = repo;
        this.survey = survey;
    }

    @Override
    protected Survey run() throws Exception {
        System.out.println("Running as command!");
        for (Question q : survey.getQuestions()) {
            if (q instanceof OptionQuestion) {
                throw new Exception();
            }
        }
        return repo.save(survey);
    }
}
