import React, { useState, useCallback } from "react";

import { Modal, FormLayout, TextField } from "@shopify/polaris";

export function NewSurveyModal({ open, openHandler }) {
  const [name, setName] = useState("");

  const handleTitleChange = useCallback(value => setName(value), []);

  const handleModalAction = async () => {
    if (name === "") {
      openHandler();
      return;
    }

    async function submit() {
      const res = await fetch(`/api/new?name=${name}`, {
        method: "POST"
      });
      await res
        .json()
        .then(res => console.log("Done!"))
        .catch(err => console.log("FAILED"));
    }

    await submit();
    openHandler();
    window.location.reload();
  };

  return (
    <Modal
      open={open}
      onClose={openHandler}
      title="Create a new survey"
      primaryAction={{
        content: "Create Survey",
        onAction: handleModalAction
      }}
    >
      <Modal.Section>
        <FormLayout>
          <TextField
            data-testid="name"
            value={name}
            onChange={handleTitleChange}
            label="Name"
            type="text"
          />
        </FormLayout>
      </Modal.Section>
    </Modal>
  );
}
