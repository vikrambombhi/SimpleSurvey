package com.example.survey.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestResource(collectionResourceRel = "question", path = "questions")
@RequestMapping("/api")
public interface QuestionRepo extends CrudRepository<Question, Long> {
    Question findById(long id);
}