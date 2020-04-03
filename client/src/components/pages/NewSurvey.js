import React, { useState } from "react";

import { Page } from "@shopify/polaris";
import { SurveyForm } from "..";

export function NewSurvey() {
  return (
    <Page
      title="Create a new survey"
      breadcrumbs={[{ content: "Admin Dashboard", url: "/app/admin/surveys" }]}
    >
      <SurveyForm />
    </Page>
  );
}
