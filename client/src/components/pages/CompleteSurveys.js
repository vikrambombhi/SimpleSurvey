import React from "react";

import { Page } from "@shopify/polaris";
import { SurveyList } from "..";

export function CompleteSurveys() {
  return (
    <Page
      title="Simple Survey"
      breadcrumbs={[{ content: "Home", url: "/app" }]}
    >
      <SurveyList />
    </Page>
  );
}
