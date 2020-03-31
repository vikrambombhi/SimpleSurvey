import React, { useState, useEffect } from "react";

import { Card, List, Badge, TextContainer, Stack } from "@shopify/polaris";

export function SurveyList({ admin = false }) {
  const [surveys, setSurveys] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const res = await fetch(`/api/surveys`);
      res
        .json()
        .then(res => setSurveys(res))
        .catch(err => setSurveys([]));
    }

    fetchData();
  }, []);

  const questionsListMarkup = questions => {
    return (
      <List type="number">
        {questions.map((question, index) => {
          const responses = question.answers
            .map(ans => ans.response || ans.val)
            .join(", ");

          return (
            <List.Item key={index}>
              {question.question} <br />
              {admin ? `Responses: ${responses}` : null}
            </List.Item>
          );
        })}
      </List>
    );
  };

  function closeSurvey(id) {
    return async function() {
      const res = await fetch(`/api/close?id=${id}`, {
        method: "POST"
      });
      res
        .json()
        .then(res => console.log(res)) // TODO chart answer data
        .catch(err => console.log(res));
    };
  }

  const surveysMarkup =
    surveys && surveys.length > 0
      ? surveys.map((survey, index) => {
          let actions = admin
            ? [{ content: "Edit Survey", url: "/app/admin/surveys/edit" }]
            : [{ content: "Complete Survey", url: "./survey/" + survey.id }];

          if (admin && !survey.closed) {
            actions.push({
              content: "Close Survey",
              onAction: closeSurvey(survey.id)
            });
          }

          return (
            <Card key={index} sectioned title={survey.name} actions={actions}>
              <Stack vertical>
                <Stack>
                  <TextContainer>
                    {questionsListMarkup(survey.questions)}
                  </TextContainer>
                </Stack>
                <Stack distribution="trailing">
                  <p>
                    <b>
                      {survey.closed ? (
                        <Badge status="info">CLOSED</Badge>
                      ) : (
                        <Badge status="success">OPEN</Badge>
                      )}
                    </b>
                  </p>
                </Stack>
              </Stack>
            </Card>
          );
        })
      : null;

  return surveysMarkup;
}
