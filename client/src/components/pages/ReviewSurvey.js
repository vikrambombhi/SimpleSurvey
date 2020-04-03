import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { Page, Spinner } from "@shopify/polaris";
import { BarChart , XAxis, YAxis, Bar } from 'recharts'

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

    const rangeQuestionCharts = (questions) => {
        if (!questions) return

        return questions
            .filter(q => q.type === "range")
            .filter(q => q.answers.length > 0)
            .map(rangeQuestionChart);
    }

    const rangeQuestionChart = (question) => {
        const histogram = histogramAnswers(question.answers);
        console.log(histogram)
        let data = Object.entries(histogram).map(([group, value]) => {
            return { name: group, value: value}
        })

        return (
            <div>
                <h1>{question.question}</h1>
                <BarChart width={600} height={300} data={data}>
                    <XAxis dataKey="name"/>
                    <YAxis/>
                    <Bar dataKey="value" fill="#8884d8"/>
                </BarChart>
            </div>
        )
    }

    // histogramAnswers generates a histogram of answers where the group number is
    // the upper bound of the group. The total number of groups is 10.
    const histogramAnswers = (questionAnswers) => {
        const answers = questionAnswers.map(a => a.val)
        const groupSize = Math.floor(Math.max(...answers) / 10)

        return answers.reduce((acc, ans) => {
            let group = Math.ceil(ans / groupSize) * groupSize
            if (!acc[group]) {
                acc[group] = 0
            }
            acc[group] += 1
            return acc
        }, {})
    }

    return (
        <Page
            title="Review Survey"
            breadcrumbs={[{ content: "Admin Dashboard", url: "/app/admin/surveys" }]}
        >
            {rangeQuestionCharts(survey.questions)}
        </Page>
    );
}
