import React, { useState } from "react";

import { Page } from "@shopify/polaris";
import { SurveyForm } from "..";

export function NewSurvey() {
	const [questions, setQuestions] = useState("");
	const addQuestion = type => {
		const values = [...questions];
		values.push({ type });
		setQuestions(values);
	};
	return (
		<Page
			title="Create a new survey"
			breadcrumbs={[{ content: "Admin Dashboard", url: "/app/admin/surveys" }]}
			actionGroups={[
				{
					title: "Add Question",
					accessibilityLabel: "Add a question",
					actions: [
						{
							content: "Range Question",
							accessibilityLabel: "Add a range question",
							onAction: () => {
								addQuestion("Range");
							}
						},
						{
							content: "Option Question",
							accessibilityLabel: "Add an option question",
							onAction: () => {
								addQuestion("Option");
							}
						},
						{
							content: "Text Question",
							accessibilityLabel: "Add a text question",
							onAction: () => {
								addQuestion("Text");
							}
						}
					]
				}
			]}
		>
			<SurveyForm questions={questions} />
		</Page>
	);
}
