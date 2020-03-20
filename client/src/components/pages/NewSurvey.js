import React from "react";

import { Page } from "@shopify/polaris";
import { SurveyForm } from "..";

export function NewSurvey() {
  return (
    <Page
      title="Create a new survey"
      breadcrumbs={[{ content: "Admin Dashboard", url: "/app/admin/surveys" }]}
      actionGroups={[{
        title: 'Add Question',
        accessibilityLabel: 'Add a question',
        actions: [
          {
            content: 'Range Question',
            accessibilityLabel: 'Add a range question',
            onAction: () => {},
          },
          {
            content: 'Option Question',
            accessibilityLabel: 'Add an option question',
            onAction: () => {},
          },
          {
            content: 'Text Question',
            accessibilityLabel: 'Add a text question',
            onAction: () => {},
          }
        ],
      }]}
    >
      <SurveyForm />
    </Page>
  );
}
