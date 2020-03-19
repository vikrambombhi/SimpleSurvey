import React from "react";

import { EmptyState } from "@shopify/polaris";

export function Home() {
  return (
    <EmptyState
      heading="Simple Surevey"
      action={{ content: "View Surveys", url: "/app/surveys" }}
      secondaryAction={{
        content: "Create Surveys",
        url: "/app/admin"
      }}
      image="https://cdn.shopify.com/s/files/1/0757/9955/files/empty-state.svg"
    >
      <p>Complete them surveys, yo!</p>
    </EmptyState>
  );
}
