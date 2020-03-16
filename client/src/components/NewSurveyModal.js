import React from "react";

import { Modal, TextContainer } from "@shopify/polaris";

export function NewSurveyModal({ open, openHandler }) {
  return (
    <Modal
      open={open}
      onClose={openHandler}
      title="Create a new survey"
      primaryAction={{
        content: "Add Survey",
        onAction: openHandler
      }}
    >
      <Modal.Section>
        <TextContainer>
          <p>TODO: Add new survey form here...</p>
        </TextContainer>
      </Modal.Section>
    </Modal>
  );
}
