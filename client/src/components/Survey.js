import React, {useEffect, useState} from "react";
import {Button, Form, Select, RangeSlider, FormLayout, TextField} from '@shopify/polaris';

export function Survey({ survey = {} }) {
    const [answers, setAnswers] = useState({});

    useEffect(() => {
        if(survey.questions) {
            survey.questions.forEach((question) => {
                answers[question.question] = null
                setAnswers(answers)
                console.log('useeffect')
            })
        }
    }, [survey])

    const questionMarkup = (question) => {
        if(question.min){
            // Range question
            return (
                <RangeSlider
                    value={answers[question.question] == null ? parseInt(question.min) : parseInt(answers[question.question])}
                    label={question.question}
                    onChange={(value, id) => {
                        const tmp = {}
                        tmp[question.question] = value
                        setAnswers({...answers, ...tmp})
                    }}
                    output
                />
            )
        } else if(question.options) {
            // Option question
            return (
                <Select
                    value={answers[question.question]}
                    label={question.question}
                    options={question.options.map((option) => {
                        return {label: option, value: option}
                    })}
                    onChange={(value, id) => {
                        const tmp = {}
                        tmp[question.question] = value
                        setAnswers({...answers, ...tmp})
                    }}
                />
            )
        } else {
            // Text question
            return (
                <TextField
                    value={answers[question.question]}
                    onChange={(value, id) => {
                        const tmp = {}
                        tmp[question.question] = value
                        setAnswers({...answers, ...tmp})
                    }}
                    label={question.question}
                    type="text"
                />
            )
        }
    }

    const surveyMarkup = (survey) => {
        if(survey.questions) {
            const questions = survey.questions.map((question) => {
                return questionMarkup(question)
            })
            return (
                <Form>
                    <FormLayout>
                        { questions }
                        <Button submit>Submit</Button>
                    </FormLayout>
                </Form>
            )
        } else return <p>LOADING</p>
    }

    return surveyMarkup(survey);
}
