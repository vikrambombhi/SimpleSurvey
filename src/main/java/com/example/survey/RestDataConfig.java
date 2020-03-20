package com.example.survey;

import com.example.survey.models.*;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.w3c.dom.ranges.Range;

@Component
public class RestDataConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Survey.class, Question.class, RangeQuestion.class, OptionQuestion.class, TextQuestion.class, Answer.class);
    }
}