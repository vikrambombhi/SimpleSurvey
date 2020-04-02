import React from "react";
import { render, fireEvent } from "@testing-library/react";
import enTranslations from "@shopify/polaris/locales/en.json";
import { AppProvider } from "@shopify/polaris";

import { EditSurveyForm } from "../components";

test("triggers the title handlers when the form title is updated", () => {
  const handleTitleChangeSpy = jest.fn();
  const newTitle = "New title";

  const editSurveyForm = render(
    <AppProvider i18n={enTranslations}>
      <EditSurveyForm
        surveyId={1}
        surveyTitle={""}
        handleSurveyTitleChange={handleTitleChangeSpy}
      />
    </AppProvider>
  );

  const titleInput = editSurveyForm.getByLabelText("Survey Title");
  fireEvent.change(titleInput, { target: { value: newTitle } });

  expect(handleTitleChangeSpy).toHaveBeenCalled();
});

test("renders all the questions passed in", () => {
  const editSurveyForm = render(
    <AppProvider i18n={enTranslations}>
      <EditSurveyForm
        surveyId={1}
        surveyTitle={""}
        handleSurveyTitleChange={jest.spy}
        handleQuestionChange={() => jest.spy}
        questions={[
          {
            type: "text",
            id: 1,
            question: "Text Question 1",
            answers: []
          },
          {
            type: "text",
            id: 2,
            question: "Text Question 2",
            answers: []
          },
          {
            type: "range",
            id: 3,
            question: "Range Question 1",
            answers: [],
            min: 1,
            max: 100
          },
          {
            type: "option",
            options: ["c", "d"],
            id: 4,
            question: "Option Question 1",
            answers: []
          }
        ]}
      />
    </AppProvider>
  );

  expect(
    editSurveyForm.getByDisplayValue("Text Question 1")
  ).toBeInTheDocument();

  expect(
    editSurveyForm.getByDisplayValue("Text Question 2")
  ).toBeInTheDocument();

  expect(
    editSurveyForm.getByDisplayValue("Range Question 1")
  ).toBeInTheDocument();
  expect(
    editSurveyForm.getByDisplayValue("Option Question 1")
  ).toBeInTheDocument();
});
