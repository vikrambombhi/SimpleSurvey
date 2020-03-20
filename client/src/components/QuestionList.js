import React from "react";

import { Card } from "@shopify/polaris";

export function QuestionList({ questions }) {
  const addQuestionBody = (type) => {
  }
  return (questions && questions.length > 0
      ? questions.map((question, index) => (
        <Card
          key={index}
          sectioned
          title={'new ' + question.type + ' question'}
        >
        </Card>
        ))
      : null)
}
