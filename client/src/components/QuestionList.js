import React, { useState, useCallback } from "react";

import { Card, TextField, Stack, Badge } from "@shopify/polaris";

export function QuestionList({ questions, isAdmin }) {
  const [questionTitle, setQuestionTitle] = useState("");
  const [rangeMin, setRangeMin] = useState("");
  const [rangeMax, setRangeMax] = useState("");
  const handleChange = useCallback((newTitle) => setQuestionTitle(newTitle), []);
  const handleMinChange = useCallback((newMin) => setRangeMin(newMin), []);
  const handleMaxChange = useCallback((newMax) => setRangeMax(newMax), []);

  return (questions && questions.length > 0
      ? questions.map((question, index) => {
        const rangeMarkup = question.type === 'Range' ?
          (<Stack distribution='equalSpacing'>
          <Badge>Min</Badge>
          <TextField type='number' onChange={handleMinChange} value={rangeMin}/>
          <Badge>Max</Badge>
          <TextField type='number' onChange={handleMaxChange} value={rangeMax}/>
          </Stack>) : null;

        return (
          <Card
            key={index}
            sectioned
            title={'New ' + question.type + ' Question'}
          >
          <TextField
            label='Question Title'
            value={questionTitle}
            type='text'
            onChange={handleChange}
          />
          {rangeMarkup}
          </Card>
        )
        })
      : null)
}
