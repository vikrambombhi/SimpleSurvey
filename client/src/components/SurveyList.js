import React, { useState, useEffect } from "react";

import { Card, List } from "@shopify/polaris";

export function SurveyList() {
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
    surveys && surveys.length > 0 ? (
      <List type="bullet">
        {surveys.map((survey, index) => (
          <List.Item key={index}>{survey.name}</List.Item>
        ))}
      </List>
    ) : null;

  return (
    <Card title="Open surveys" sectioned>
      {surveysMarkup}
    </Card>
  );
}
