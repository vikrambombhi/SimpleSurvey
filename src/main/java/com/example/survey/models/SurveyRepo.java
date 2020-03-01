package com.example.survey.models;

import org.springframework.data.repository.CrudRepository;

public interface SurveyRepo extends CrudRepository<Survey, Long> {
    Survey findById(long id);
}
