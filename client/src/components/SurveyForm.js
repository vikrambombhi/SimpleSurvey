import React, { useState, useCallback } from "react";

import { Form, FormLayout, TextField, Button, Card, Popover, ActionList } from "@shopify/polaris";
import { QuestionList } from "./QuestionList.js";

export function SurveyForm() {
  const [name, setName] = useState("");
  const [questions, setQuestions] = useState([]);
  const [active, setActive] = useState(true);
  const handleTitleChange = useCallback(value => setName(value), []);
  const toggleActive = useCallback(() => setActive((active) => !active), []);
  const strToArray = str => str?.replace(/\s/g, "").split(",");
  const addQuestion = type => {
    const values = [...questions];
    values.push({ type });
    setQuestions(values);
  };
  const getQuestions = () => {
    return [...questions];
  };
  const activator = (
    <Button onClick={toggleActive} disclosure>
      Add a question
    </Button>
  );

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

          <Popover active={active} activator={activator} onClose={toggleActive}>
            <ActionList
              items = {[
                {
                  content: "Range Question",
                  accessibilityLabel: "Add a range question",
                  onAction: () => {
                    addQuestion("range");
                    toggleActive();
                  }
                },
                {
                  content: "Option Question",
                  accessibilityLabel: "Add an option question",
                  onAction: () => {
                    addQuestion("option");
                    toggleActive();
                  }
                },
                {
                  content: "Text Question",
                  accessibilityLabel: "Add a text question",
                  onAction: () => {
                    addQuestion("text");
                    toggleActive();
                  }
                }
              ]}
            />
          </Popover>

          <Button submit>Submit</Button>
        </FormLayout>
      </Form>
    </Card>
  );
}
