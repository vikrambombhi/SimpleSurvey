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

  function closeSurvey(id) {
    return async function() {
      const res = await fetch(`/api/close?id=${id}`, {
        method: "POST"
      });
      res
        .json()
        .then(res => console.log(res)) // TODO chart answer data
        .catch(err => console.log(res));
    }
  }

  const surveysMarkup =
    surveys && surveys.length > 0
      ? surveys.map((survey, index) => {
          let actions = admin
                ? [{ content: "Edit Survey", url: "/app/admin/surveys/edit" }]
                : [{ content: "Complete Survey", url: "./todo" }]

          if (admin && !survey.closed) {
            actions.push({ content: "Close Survey", onAction: closeSurvey(survey.id) })
          }

          return (
            <Card
              key={index}
              sectioned
              title={survey.name}
              actions={actions}
            >
              <p><b>{survey.closed ? "CLOSED" : "OPEN"}</b></p>
              <p>TODO: list questions from this survey here</p>
            </Card>
          )
      })
      : null;

  return surveysMarkup;
}
