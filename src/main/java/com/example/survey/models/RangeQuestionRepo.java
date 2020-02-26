package com.example.survey.models;

import org.springframework.data.repository.CrudRepository;

public interface RangeQuestionRepo extends CrudRepository<RangeQuestion, Long> {
    RangeQuestion findById(long id);
}
