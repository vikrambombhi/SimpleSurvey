import React, { useEffect, useState } from "react";
import {
  Button,
  Form,
  Select,
  RangeSlider,
  FormLayout,
  TextField
} from "@shopify/polaris";

export function Survey({ survey = {} }) {
  const [questions, setQuestions] = useState([]);
  const [fieldErrors, setFieldErrors] = useState([]);

  useEffect(() => {
    let qs = survey.questions || [];
    setQuestions(
      qs.map(q => {
        switch (q.type) {
          case "range":
            q.answers = [{val: parseInt(q.min)}];
            break
          case "option":
            q.answers = [{response: q.options[0]}];
            break
          default:
            q.answers = []
        }
        return q;
      })
    );
  }, [survey]);

  async function submitAnswers() {
    if (hasBlankAnswers()) {
      showValidationErrors()
      return
    }
    const res = await fetch(`/api/answers`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(questions)
    });

    await res.json().catch(err => console.log(err));
  }

  function hasBlankAnswers() {
    return questions.some(q =>  q.answers.length > 0 )
  }

  function showValidationErrors() {
    setFieldErrors(questions.map((q) =>  {
      if (q.answers.length) return ""
      return "This field is required"
    }))
  }

  const questionMarkup = (question, index) => {
    switch (question.type) {
      case "range":
        return (
          <RangeSlider
            value={question.answers[0]?.val || parseInt(question.min)}
            label={question.question}
            error={fieldErrors[index]}
            onChange={(value, id) => {
              questions[index].answers = [{ val: value }];
              setQuestions([...questions]);
            }}
            output
          />
        );
      case "option":
        return (
          <Select
            value={question.answers[0]?.response || ""}
            label={question.question}
            error={fieldErrors[index]}
            options={question.options.map(option => {
              return { label: option, value: option };
            })}
            onChange={(value, id) => {
              questions[index].answers = [{ response: value }];
              setQuestions([...questions]);
            }}
          />
        );
      case "text":
        return (
          <TextField
            value={question.answers[0]?.response || ""}
            error={fieldErrors[index]}
            onChange={(value, id) => {
              questions[index].answers = [{ response: value }];
              setQuestions([...questions]);
            }}
            label={question.question}
            type="text"
          />
        );
      default:
        console.log("invalid question type");
    }
  };

  const surveyMarkup = () => {
    let qs = questions.map((question, index) => {
      return questionMarkup(question, index);
    });
    return (
      <Form>
        <FormLayout>
          {qs.length ? qs : <p>LOADING</p>}
          <Button onClick={submitAnswers}>Submit</Button>
        </FormLayout>
      </Form>
    );
  };
  return surveyMarkup();
}
