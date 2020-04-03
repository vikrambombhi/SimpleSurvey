import React, { useState, useEffect } from "react";

import { Card, List } from "@shopify/polaris";

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

  const answerMarkup = answer => {
    if ("response" in answer) {
      return <List.Item>{answer.response}</List.Item>;
    }
    return <List.Item>{answer.val}</List.Item>;
  };

  const answerListMarkup = answers => {
    return <List type="number">{answers.map(answerMarkup).slice(0, 3)}</List>;
  };

  const questionsListMarkup = questions => {
    return (
      <List type="bullet">
        {questions.map(question => {
          return (
            <div>
              <List.Item> {question.question} </List.Item>
              <p>Sample Answers</p>
              {answerListMarkup(question.answers)}
            </div>
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
      window.location.reload(false);
      res
        .json()
        .then(res => console.log(res)) // TODO chart answer data
        .catch(err => console.log(res));
    };
  }

  const surveysMarkup =
    surveys && surveys.length > 0
      ? surveys.map((survey, index) => {
          let actions = [];
          if (admin) {
            if (!survey.closed) {
              actions.push({
                content: "Edit Survey",
                url: `/app/admin/surveys/edit/${survey.id}`
              });
              actions.push({
                content: "Close Survey",
                onAction: closeSurvey(survey.id)
              });
            }
            actions.push({
              content: "View Results",
              url: `/app/admin/surveys/review/${survey.id}`
            });
          } else {
            if (!survey.closed) {
              actions.push({
                content: "Complete Survey",
                url: "./survey/" + survey.id
              });
            }
          }

          return (
            <Card key={index} sectioned title={survey.name} actions={actions}>
              <p>
                <b>{survey.closed ? "CLOSED" : "OPEN"}</b>
              </p>
              {questionsListMarkup(survey.questions)}
            </Card>
          );
        })
      : null;

  return surveysMarkup;
}
