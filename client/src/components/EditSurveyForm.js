import React from "react";

import {
  Form,
  FormLayout,
  TextField,
  Button,
  Card,
  Stack
} from "@shopify/polaris";

export function EditSurveyForm({
  surveyId,
  surveyTitle,
  handleSurveyTitleChange,
  questions = [],
  handleQuestionChange
}) {
  const handleSubmit = async () => {
    const updateTitleRequest = fetch(`/api/surveys/${surveyId}`, {
      method: "PUT",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ name: surveyTitle })
    });

    function questionRequestHelper(question, body) {
      return fetch(`/api/questions/${question.id}`, {
        method: "PUT",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
      });
    }

    const questionRequests = questions.map(question => {
      if (question.type === "text") {
        return questionRequestHelper(question, {
          type: "text",
          question: question.question
        });
      }
      if (question.type === "range") {
        return questionRequestHelper(question, {
          type: "range",
          question: question.question,
          min: question.min,
          max: question.max
        });
      }
      if (question.type === "option") {
        return questionRequestHelper(question, {
          type: "option",
          question: question.question,
          options: question.options
        });
      }
    });

    await Promise.all([updateTitleRequest, questionRequests]);
    window.location = "/app/admin/surveys";
  };

  const questionsMarkup = questions.map((question, index) => {
    if (question.type === "range") {
      return (
        <Card sectioned key={index}>
          <Stack distribution="fill" alignment="center" spacing="loose">
            <TextField
              label={`${index + 1}: Range question`}
              value={question.question}
              type="text"
              onChange={handleQuestionChange(index)}
            />
            <Stack distribution="trailing">
              <TextField
                label="Min"
                type="number"
                onChange={handleQuestionChange(index, "min")}
                value={question.min.toString()}
              />
              <TextField
                label="Max"
                type="number"
                onChange={handleQuestionChange(index, "max")}
                value={question.max.toString()}
              />
            </Stack>
          </Stack>
        </Card>
      );
    }

    // TODOD HANDLE CSV input
    if (question.type === "option") {
      return (
        <Card sectioned key={index}>
          <TextField
            type="text"
            label={`${index + 1}: Option question`}
            value={question.question}
            onChange={handleQuestionChange(index)}
          />
          <TextField
            type="text"
            label={`Options`}
            value={question.options.join(",")}
            helpText="Comma separated value of options"
            onChange={handleQuestionChange(index, "options")}
          />
        </Card>
      );
    }
    if (question.type === "text") {
      return (
        <Card sectioned key={index}>
          <TextField
            label={`${index + 1}: Text question`}
            value={question.question}
            type="text"
            onChange={handleQuestionChange(index)}
          />
        </Card>
      );
    }

    return null;
  });

  return (
    <Card sectioned>
      <Form onSubmit={handleSubmit}>
        <FormLayout>
          <TextField
            data-testid="name"
            value={surveyTitle}
            onChange={handleSurveyTitleChange}
            label="Survey Title"
            type="text"
          />

          {questionsMarkup}

          <Button submit primary>
            Edit
          </Button>
        </FormLayout>
      </Form>
    </Card>
  );
}
