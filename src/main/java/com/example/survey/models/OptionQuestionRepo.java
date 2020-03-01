package com.example.survey.models;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OptionQuestionRepo extends CrudRepository<OptionQuestion, Long> {
    Optional<OptionQuestion> findById(Long id);
}
