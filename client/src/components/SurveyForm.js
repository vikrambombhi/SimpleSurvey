import React, { useState, useCallback } from "react";

import { Form, FormLayout, TextField, Button, Card } from "@shopify/polaris";

export function SurveyForm() {
  const [name, setName] = useState("");

  const handleTitleChange = useCallback(value => setName(value), []);

  const handleSubmit = async () => {
    if (name === "") {
      return;
    }

    async function submit() {
      const res = await fetch(`/api/new`, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            name: `${name}`,
        })
      });
      await res
        .json()
        .then(res => console.log("Done!"))
        .catch(err => console.log("FAILED"));
    }

    await submit();
    window.location.href = "/app/admin/surveys";
  };

  return (
    <Card sectioned>
      <Form onSubmit={handleSubmit}>
        <FormLayout>
          <TextField
            data-testid="name"
            value={name}
            onChange={handleTitleChange}
            label="Name"
            type="text"
          />

          <Button submit>Submit</Button>
        </FormLayout>
      </Form>
    </Card>
  );
}