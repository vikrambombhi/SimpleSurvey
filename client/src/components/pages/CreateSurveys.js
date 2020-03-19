import React, { useState, useCallback } from "react";

import { Page } from "@shopify/polaris";
import { NewSurveyModal } from "../NewSurveyModal";
import { SurveyList } from "../SurveyList";

export function CreateSurveys() {
  const [openModal, setOpenModal] = useState(false);
  const toggleModalHandler = useCallback(() => {
    setOpenModal(!openModal);
  }, [openModal]);

  return (
    <Page
      title="Simple Survey"
      primaryAction={{
        content: "Create Survey",
        onAction: toggleModalHandler
      }}
      breadcrumbs={[{ content: "Home", url: "/app" }]}
    >
      <NewSurveyModal open={openModal} openHandler={toggleModalHandler} />
      <SurveyList admin />
    </Page>
  );
}
