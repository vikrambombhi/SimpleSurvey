import React, { useState, useCallback } from "react";

import { Form, FormLayout, TextField, Button, Card } from "@shopify/polaris";

export function NewPageForm() {
  const [id, setId] = useState("");
  const [title, setTitle] = useState("");

  const handleSubmit = useCallback(() => {
    alert(`TODO: Submit form with values: ${id}:${title}`);
  }, [id, title]);

  const handleIdChange = useCallback(value => setId(value), []);
  const handelTitleChange = useCallback(value => setTitle(value), []);

  return (
    <Card title="Add question to survery" sectioned>
      <Form onSubmit={handleSubmit}>
        <FormLayout>
          <TextField
            value={id}
            onChange={handleIdChange}
            label="ID"
            type="number"
          />

          <TextField
            value={title}
            onChange={handelTitleChange}
            label="Title"
            type="text"
          />

          <Button submit>Submit</Button>
        </FormLayout>
      </Form>
    </Card>
  );
}
