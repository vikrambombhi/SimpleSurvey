package com.example.survey;

import com.example.survey.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SurveyApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SurveyRepo surveyRepo;

	SurveyApplicationTests() {
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void newSurvey() throws Exception {

		MvcResult r = this.mockMvc.perform(post("/api/new")
				.param("name", "joe"))
				.andExpect(status().isOk())
				.andReturn();

		Survey s = this.objectMapper.readValue(r.getResponse().getContentAsString(), Survey.class);

		this.mockMvc.perform(post("/api/survey")
				.param("id", String.valueOf(s.getId())))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("joe")))
				.andExpect(jsonPath("$.closed", is(false)))
				.andExpect(jsonPath("$.questions", empty()));
	}

	@Test
	void closeSurvey() throws Exception {
		MvcResult r = this.mockMvc.perform(post("/api/new")
				.param("name", "joe"))
				.andExpect(status().isOk())
				.andReturn();

		Survey s = this.objectMapper.readValue(r.getResponse().getContentAsString(), Survey.class);

		this.mockMvc.perform(post("/api/close")
				.param("id", String.valueOf(s.getId())))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.closed", is(true)));
	}

	@Test
	void closeSurveyReturnsAnswers() throws Exception {
		TextQuestion tq = new TextQuestion("Text Question");
		tq.addAnswer(new Answer("foo"));
		tq.addAnswer(new Answer("bar"));

		RangeQuestion rq = new RangeQuestion("Range Question", 1, 100);
		rq.addAnswer(new Answer(5));
		rq.addAnswer(new Answer(69));

		Survey survey = new Survey("Test Survey");
		survey.addQuestion(tq);
		survey.addQuestion(rq);

		this.surveyRepo.save(survey);

		this.mockMvc.perform(post("/api/close")
				.param("id", String.valueOf(survey.getId())))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.closed", is(true)))
				.andExpect(jsonPath("$.questions[*].question", containsInAnyOrder("Text Question", "Range Question")))
				.andExpect(jsonPath("$.questions[*].answers[*].response", containsInAnyOrder("foo", "bar", null, null)))
				.andExpect(jsonPath("$.questions[*].answers[*].val", containsInAnyOrder(5, 69, 0, 0)));
	}

	@Test
	void surveysHasID() throws Exception {
		this.mockMvc.perform(get("/api/surveys"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("_embedded.survey[*].id",  notNullValue()))
				.andExpect(jsonPath("_embedded.survey[*].questions[*].id",  notNullValue()));
		;
	}
}
