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
      const updatedQuestions = questions.map((question, idx) => {
        if (extraOpts && index === idx) {
          return {
            ...question,
            ...(extraOpts === "min" ? { min: newValue } : {}),
            ...(extraOpts === "max" ? { max: newValue } : {}),
            ...(extraOpts === "options" ? { options: newValue.split(",") } : {})
          };
        }
        if (index === idx) {
          return {
            ...question,
            question: newValue
          };
        }

        return question;
      });

      setQuestions(updatedQuestions);
    };
  }

  useEffect(() => {
    async function fetchData() {
      const res = await fetch(`/api/surveys`);
      res
        .json()
        .then(res => {
          const surveyToEdit = res.find(sur => sur.id == surveyId);
          console.log("surveyToEdit: ", surveyToEdit);
          setName(surveyToEdit.name);
          setQuestions(surveyToEdit.questions);
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
