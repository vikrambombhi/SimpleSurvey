import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { Page, Spinner, Stack } from "@shopify/polaris";
import { EditSurveyForm } from "..";

export function EditSurvey() {
  let { id: surveyId } = useParams();

  const [name, setName] = useState("");
  const [questions, setQuestions] = useState([]);

  // a curry function that we will pass down to EditSurveyForm to update state
  // This will be our state updated for all our survey files
  // index will be the index of the question in in survey.questions,
  // extraOpts is for updating mins/max in range questions or options in option questions
  function handleQuestionChange(index, extraOpts) {
    return newValue => {
      let updatedQuestions = [...questions];
      if (extraOpts) {
        updatedQuestions[index] = {
          ...updatedQuestions[index],
          ...(extraOpts === "min" ? { min: newValue } : {}),
          ...(extraOpts === "max" ? { max: newValue } : {}),
          ...(extraOpts === "options" ? { options: newValue.split(",") } : {})
        };
      } else {
        updatedQuestions[index] = {
          ...updatedQuestions[index],
          question: newValue
        };
      }

      setQuestions(updatedQuestions);
    };
  }

  useEffect(() => {
    async function fetchData() {
      const res = await fetch(`/api/survey?id=` + surveyId);
      res
        .json()
        .then(res => {
          // eslint-disable-next-line
          setName(res.name);
          setQuestions(res.questions);
        })
        .catch(err => setQuestions([]));
    }

    fetchData();
  }, [surveyId]);

  const pageContent =
    name === "" && questions === [] ? (
      <Stack distribution="center">
        <Spinner />
      </Stack>
    ) : (
      <EditSurveyForm
        surveyId={surveyId}
        questions={questions}
        surveyTitle={name}
        handleSurveyTitleChange={setName}
        handleQuestionChange={handleQuestionChange}
      />
    );

  return (
    <Page
      title="Edit Survey"
      breadcrumbs={[{ content: "Admin Dashboard", url: "/app/admin/surveys" }]}
    >
      {pageContent}
    </Page>
  );
}
