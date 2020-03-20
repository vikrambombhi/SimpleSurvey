import React from "react";

import { Card, TextField, Stack, Badge } from "@shopify/polaris";

export function QuestionList({ setQuestions, getQuestions }) {
  const addMin = (newMin, index) => {
    const values = getQuestions();
    values[index].min = newMin;
    setQuestions(values);
  };
  const addMax = (newMax, index) => {
    const values = getQuestions();
    values[index].max = newMax;
    setQuestions(values);
  };
  const addOption = (newOptions, index) => {
    const values = getQuestions();
    values[index].options = newOptions;
    setQuestions(values);
  };
  const addTitle = (newTitle, index) => {
    const values = getQuestions();
    values[index].title = newTitle;
    setQuestions(values);
  };

  const questions = getQuestions();
  return questions && questions.length > 0
    ? questions.map((question, index) => {
        const rangeMarkup =
          question.type === "Range" ? (
            <Stack distribution="equalSpacing">
              <Badge>Min</Badge>
              <TextField
                type="number"
                onChange={val => {
                  addMin(val, index);
                }}
                value={getQuestions()[index].min}
              />
              <Badge>Max</Badge>
              <TextField
                type="number"
                onChange={val => {
                  addMax(val, index);
                }}
                value={getQuestions()[index].max}
              />
            </Stack>
          ) : null;

        const optionMarkup =
          question.type === "Option" ? (
            <TextField
              type="text"
              label="Options"
              value={getQuestions()[index].options}
              helpText="Comma separated value of options"
              onChange={val => {
                addOption(val, index);
              }}
            />
          ) : null;

        return (
          <Card
            key={index}
            sectioned
            title={"New " + question.type + " Question"}
          >
            <TextField
              label="Question Title"
              value={getQuestions()[index].title}
              type="text"
              onChange={val => {
                addTitle(val, index);
              }}
            />
            {rangeMarkup}
            {optionMarkup}
          </Card>
        );
      })
    : null;
}
