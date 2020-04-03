import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { Page, Spinner, Stack, Card, List } from "@shopify/polaris";
import { BarChart , XAxis, YAxis, Bar, Pie, PieChart, Tooltip } from 'recharts'

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

    const optionQuestionChart = (answers) => {
        const optionCount = answers.reduce((accumulator, answer) => {
            if (accumulator[answer.response] === undefined) {
                accumulator[answer.response] = 1
            } else {
                accumulator[answer.response]++
            }
            return accumulator
        }, {})

        const data = Object.keys(optionCount).map(optionName => {
            return {name: optionName, value: optionCount[optionName]}
        })

        return (
            <PieChart width={800} height={400}>
                <Pie isAnimationActive={true} data={data} cx={200} cy={200} outerRadius={80} fill="#41ead4" label/>
                <Tooltip/>
            </PieChart>
        )
    }

    const rangeQuestionChart = (answers) => {
        const histogram = histogramAnswers(answers);
        let data = Object.entries(histogram).map(([group, value]) => {
            return { name: group, value: value}
        })

        return (
            <BarChart width={600} height={300} data={data}>
                <XAxis dataKey="name"/>
                <YAxis/>
                <Bar dataKey="value" fill="#8884d8"/>
            </BarChart>
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

    const answerChart = (type, answers) => {
        if (answers.length === 0) {
            return <p>No answers yet =( </p>
        }
        if (type === "range") {
            return rangeQuestionChart(answers)
        }
        else if (type === "option") {
            return optionQuestionChart(answers)
        }
        return <List>
            {
                answers.map((answer) => {
                    return <List.Item>{answer.val}</List.Item>
                })
            }
        </List>
    }

    const questionsContent = (questions) => {
        return questions.map((question) => {
            return (
                <Card title={question.question}>
                    { answerChart(question.type, question.answers)}
                </Card>
            )
        })
    }

    const pageContent = (survey) => {
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
            { pageContent(survey) }
        </Page>
    );
}
