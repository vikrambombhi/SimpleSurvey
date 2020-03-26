import React, { useState, useCallback } from "react";

import { Form, FormLayout, TextField, Button, Card } from "@shopify/polaris";
import { QuestionList } from "./QuestionList.js";

export function SurveyForm({ setQuestions, getQuestions }) {
  const [name, setName] = useState("");
  const handleTitleChange = useCallback(value => setName(value), []);
  const strToArray = str => str.replace(/\s/g, "").split(",");

  const formatQuestions = questions =>
    questions.map(q => {
      if (q.type === "option")
        return {
          type: q.type,
          question: q.question,
          options: strToArray(q.options)
        };
      return q;
    });

  const handleSubmit = async () => {
    if (name === "") {
      return;
    }

    async function submit() {
      const res = await fetch(`/api/new`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          name,
          closed: false,
          questions: formatQuestions(getQuestions())
        })
      });
      await res
        .json()
        .then(res => console.log("Done!"))
        .catch(err => console.log("FAILED"));
    }

    await submit();
    window.location.href = "/app/admin/surveys";
  };

  return (
    <Card sectioned>
      <Form onSubmit={handleSubmit}>
        <FormLayout>
          <TextField
            data-testid="name"
            value={name}
            onChange={handleTitleChange}
            label="Name"
            type="text"
          />

          <QuestionList
            setQuestions={setQuestions}
            getQuestions={getQuestions}
          />

          <Button submit>Submit</Button>
        </FormLayout>
      </Form>
    </Card>
  );
}
