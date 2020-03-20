import React from "react";
import { render, fireEvent } from "@testing-library/react";
import enTranslations from "@shopify/polaris/locales/en.json";
import { AppProvider } from "@shopify/polaris";

import { SurveyForm } from "../components";

const setup = () => {
  const utils = render(
    <AppProvider i18n={enTranslations}>
      <SurveyForm />
    </AppProvider>
  );
  const input = utils.getByLabelText("Name");
  return {
    nameInput: input,
    ...utils
  };
};

test("sets the name on the form", () => {
  const surverName = "Some new survey";
  const { nameInput } = setup();

  fireEvent.change(nameInput, { target: { value: surverName } });
  expect(nameInput.value).toBe(surverName);
});
