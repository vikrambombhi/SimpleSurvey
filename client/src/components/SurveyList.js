import React, { useState, useEffect } from "react";

import { Card } from "@shopify/polaris";

export function SurveyList({ admin = false }) {
  const [surveys, setSurveys] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const res = await fetch(`/api/surveys`);
      res
        .json()
        .then(res => setSurveys(res["_embedded"].survey || []))
        .catch(err => setSurveys([]));
    }

    fetchData();
  }, []);

    const questionsListMarkup = (questions) => {
        return questions.map(question => {
            return <p key={question.question}>{question.question}</p>
        })
    }


    const surveysMarkup = (surveys) => {
        if(!surveys || surveys.length < 0) {
            return null
        }
        return surveys.map((survey, index) => (
            <Card
                key={index}
                sectioned
                title={survey.name}
                actions={
                    admin
                        ? [{ content: "Edit Survey", url: "./todo" }]
                        : [{ content: "Complete Survey", url: "./todo" }]
                }
            >
                { questionsListMarkup(survey.questions) }
            </Card>
        ))
    }

    return surveysMarkup(surveys);

}
