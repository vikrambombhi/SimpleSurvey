package com.example.survey.models;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "survey", path = "surveys")
public interface SurveyRepo extends CrudRepository<Survey, Long> {
    Survey findById(@Param("id") Integer id);
}
