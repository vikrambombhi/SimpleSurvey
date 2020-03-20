import React, { useState, useCallback } from "react";

import { Card, TextField, Stack, Badge } from "@shopify/polaris";

export function QuestionList({ questions, isAdmin }) {
  const [questionTitles, setQuestionTitles] = useState([]);
  const [rangeMin, setRangeMin] = useState("");
  const [rangeMax, setRangeMax] = useState("");
  const [options, setOptions] = useState("");
  const handleQuestionTitles = useCallback(newTitle => {
    const values = [...questionTitles];
    values.push(newTitle);
    setQuestionTitles(values);
  }, []);
  const handleMinChange = useCallback(newMin => setRangeMin(newMin), []);
  const handleMaxChange = useCallback(newMax => setRangeMax(newMax), []);
  const handleOptionChange = useCallback(
    newOptions => setOptions(newOptions),
    []
  );

  return questions && questions.length > 0
    ? questions.map((question, index) => {
        const rangeMarkup =
          question.type === "Range" ? (
            <Stack distribution="equalSpacing">
              <Badge>Min</Badge>
              <TextField
                type="number"
                onChange={handleMinChange}
                value={rangeMin}
              />
              <Badge>Max</Badge>
              <TextField
                type="number"
                onChange={handleMaxChange}
                value={rangeMax}
              />
            </Stack>
          ) : null;

        const optionMarkup =
          question.type === "Option" ? (
            <TextField
              type="text"
              label="Options"
              value={options}
              helpText="Comma separated value of options"
              onChange={handleOptionChange}
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
              value={questionTitles[index]}
              type="text"
              onChange={handleQuestionTitles[index]}
            />
            {rangeMarkup}
            {optionMarkup}
          </Card>
        );
      })
    : null;
}
