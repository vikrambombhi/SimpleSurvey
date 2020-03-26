package com.example.survey.controllers;

import com.example.survey.models.Survey;
import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SurveyControllerTest {
    @Autowired
    private SurveyController sc;

    @Test
    public void contexLoads() throws Exception {
        assertThat(this.sc).isNotNull();
    }

    @Test
    void getSurveysCircuitBreaker() {
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.getSurveys.circuitBreaker.forceOpen", "true");

        Iterable<Survey> surveys = sc.getSurveys();
        assertEquals(false, surveys.iterator().hasNext());
    }

    @Test
    void newSurveysCircuitBreaker() {
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.newSurvey.circuitBreaker.forceOpen", "true");

        try {
            this.sc.newSurvey(null);
            fail("should've short circuited");
        } catch (HystrixRuntimeException e) {
            assertEquals("Hystrix circuit short-circuited and is OPEN", e.getCause().getMessage());
        }

    }
}