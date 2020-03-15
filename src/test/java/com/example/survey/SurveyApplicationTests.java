package com.example.survey;

import com.example.survey.models.Survey;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SurveyApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	SurveyApplicationTests() {
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void newSurvey() throws Exception {

		MvcResult r = this.mockMvc.perform(post("/new")
				.param("name", "joe"))
				.andExpect(status().isOk())
				.andReturn();

		Survey s = this.objectMapper.readValue(r.getResponse().getContentAsString(), Survey.class);

		this.mockMvc.perform(post("/survey")
				.param("id", String.valueOf(s.getId())))
				.andExpect(jsonPath("$.name", is("joe")))
				.andExpect(jsonPath("$.closed", is(false)))
				.andExpect(jsonPath("$.questions", empty()));
	}

	@Test
	void closeSurvey() throws Exception {
		MvcResult r = this.mockMvc.perform(post("/new")
				.param("name", "joe"))
				.andExpect(status().isOk())
				.andReturn();

		Survey s = this.objectMapper.readValue(r.getResponse().getContentAsString(), Survey.class);

		this.mockMvc.perform(post("/close")
				.param("id", String.valueOf(s.getId())))
				.andExpect(jsonPath("$.closed", is(true)));
	}
}
