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

  useEffect(() => {
    let qs = survey.questions || [];
    setQuestions(
      qs.map(q => {
        q.answers = [];
        return q;
      })
    );
  }, [survey]);

  const handleSubmit = async () => {
    async function submitAnswers() {
      const applyDefaultsIfOmitted = questions.map(question => {
        if (question.answers.length > 0) {
          return question;
        }
        if (question.type === "text") {
          return { ...question, answers: [""] };
        }
        if (question.type === "option") {
          return { ...question, answers: [question.options[0]] };
        }
        if (question.type === "range") {
          return { ...question, answers: [question.min] };
        }

        return question;
      });

      await fetch(`/api/answers`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(applyDefaultsIfOmitted)
      })
        .then(res => console.log("Done!"))
        .catch(err => console.log("FAILED: ", err));
    }

    await submitAnswers();
    window.location.href = "/app/surveys";
  };

  const questionMarkup = (question, index) => {
    switch (question.type) {
      case "range":
        return (
          <RangeSlider
            key={index}
            value={question.answers[0]?.val || parseInt(question.min)}
            label={question.question}
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
            key={index}
            value={question.answers[0]?.response || ""}
            label={question.question}
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
            key={index}
            value={question.answers[0]?.response || ""}
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

  let qs = questions.map((question, index) => {
    return questionMarkup(question, index);
  });

  return (
    <Form onSubmit={handleSubmit}>
      <FormLayout>
        {qs.length ? qs : <p>LOADING</p>}
        <Button submit primary>
          Submit
        </Button>
      </FormLayout>
    </Form>
  );
}
