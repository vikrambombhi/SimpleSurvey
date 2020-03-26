package com.example.survey.models;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestResource(collectionResourceRel = "survey", path = "surveys")
@RequestMapping("/api")
public interface SurveyRepo extends CrudRepository<Survey, Long> {
    Survey findById(long id);
}
