package com.example.survey;

import com.example.survey.models.Answer;
import com.example.survey.models.Question;
import com.example.survey.models.Survey;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RestDataConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Survey.class, Question.class, Answer.class);
    }
}