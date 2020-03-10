import React, { useState, useCallback } from "react";

import enTranslations from "@shopify/polaris/locales/en.json";
import { AppProvider, Page } from "@shopify/polaris";
import { NewPageForm } from "./NewSurveyForm";
import { NewSurveyModal } from "./NewSurveyModal";
import { SurveyList } from "./SurveyList";

export function App() {
  const [openModal, setOpenModal] = useState(false);
  const toggleModalHandler = useCallback(() => {
    setOpenModal(!openModal);
  }, [openModal]);

  return (
    <AppProvider i18n={enTranslations}>
      <Page
        title="Simple Survey"
        primaryAction={{
          content: "Create Survey",
          onAction: toggleModalHandler
        }}
      >
        <NewPageForm />
        <NewSurveyModal open={openModal} openHandler={toggleModalHandler} />
        <SurveyList />
      </Page>
    </AppProvider>
  );
}
