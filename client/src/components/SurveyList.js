import React, { useState, useEffect } from "react";

import { Card } from "@shopify/polaris";

export function SurveyList({ admin = false }) {
  const [surveys, setSurveys] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const res = await fetch(`/surveys`);
      res
        .json()
        .then(res => setSurveys(res["_embedded"].survey || []))
        .catch(err => setSurveys([]));
    }

    fetchData();
  }, []);

  const surveysMarkup =
    surveys && surveys.length > 0
      ? surveys.map((survey, index) => (
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
            <p>TODO: list questions from this survey here</p>
          </Card>
        ))
      : null;

  return surveysMarkup;
}
