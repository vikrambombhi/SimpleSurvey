import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { Page, Spinner, Stack } from "@shopify/polaris";
import { Chart } from 'react-charts'

export function ReviewSurvey() {
    let { id: surveyId } = useParams();

    const [survey, setSurvey] = useState({});

    useEffect(() => {
        async function fetchData() {
            const res = await fetch(`/api/survey?id=${surveyId}`);
            res
                .json()
                .then(res => {
                    setSurvey(res);
                })
                .catch(err => setSurvey({}));
        }

        fetchData();
    }, [surveyId]);

    const answerChart = (type, answers) => {
        if (type === "range") {
            return

        }
        return answers.map((answer) => {
            return <p>{answer.val}</p>
        })
    }

    const questionsContent = (questions) => {
        return questions.map((question) => {
            return (
                <div>
                    <h1>{question.question}</h1>
                    { answerChart(question.type, question.answers)}
                </div>
            )
        })
    }

    const pageContent = (survey) => {
        console.log("survey is ", survey)
        if(Object.keys(survey).length === 0 && survey.constructor === Object) {
            // eslint-disable-next-line
            return <Stack distribution="center">
                <Spinner/>
            </Stack>
        } else {
            return questionsContent(survey.questions)
        }
    }

    return (
        <Page
            title="Review Survey"
            breadcrumbs={[{ content: "Admin Dashboard", url: "/app/admin/surveys" }]}
        >
            {pageContent(survey)}
        </Page>
    );
}
