package com.example.survey.models;

import org.springframework.data.repository.CrudRepository;

public interface TextQuestionRepo extends CrudRepository<TextQuestion, Long> {
  TextQuestion findById(long id);
}
