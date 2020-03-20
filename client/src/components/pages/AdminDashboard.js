import React from "react";

import { Page } from "@shopify/polaris";
import { SurveyList } from "..";

export function AdminDashboard() {
  return (
    <Page
      title="Admin Dashboard"
      primaryAction={{
        content: "Create Survey",
        url: "/app/admin/surveys/new"
      }}
      breadcrumbs={[{ content: "Home", url: "/app" }]}
    >
      <SurveyList admin />
    </Page>
  );
}
