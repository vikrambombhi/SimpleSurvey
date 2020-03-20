import React, {useEffect, useState} from "react";
import {Button, Form, Select, RangeSlider, FormLayout, TextField} from '@shopify/polaris';

export function Survey({ survey = {} }) {
    const [answers, setAnswers] = useState({});

    useEffect(() => {
        if(survey.questions) {
            survey.questions.forEach((question) => {
                answers[question.question] = null
                setAnswers(answers)
            })
        }
        // eslint-disable-next-line
    }, [survey])

    async function submitAnswers() {
        const b = []
        console.log(answers)
        for (let [name, value] of Object.entries(answers)) {
            console.log(name, value)
            b.push(value)
        }
        console.log(b)
        const body = JSON.stringify(b)
      const res = await fetch(`/api/answers`, {
        method: "POST",
        headers: {
          "Accept": "application/json",
          "Content-Type": "application/json"
        },
        body: body
      });

      await res
        .json()
        .then(res => console.log(res))
        .catch(err => console.log(err));
    }

    const questionMarkup = (question) => {
        if(question.min){
            // Range question
            return (
                <RangeSlider
                    value={answers[question.question] == null ? parseInt(question.min) : parseInt(answers[question.question].val)}
                    label={question.question}
                    onChange={(value, id) => {
                        const tmp = {}
                        tmp[question.question] = {
                            val: value,
                            question: {id: question.id, type: question.type},
                        }
                        setAnswers({...answers, ...tmp})
                    }}
                    output
                />
            )
        } else if(question.options) {
            // Option question
            return (
                <Select
                    value={answers[question.question] == null ? '' : answers[question.question].response}
                    label={question.question}
                    options={question.options.map((option) => {
                        return {label: option, value: option}
                    })}
                    onChange={(value, id) => {
                        const tmp = {}
                        tmp[question.question] = {
                            response: value,
                            question: {id: question.id, type: question.type},
                        }
                        setAnswers({...answers, ...tmp})
                    }}
                />
            )
        } else {
            // Text question
            return (
                <TextField
                    value={answers[question.question] == null ? '' : answers[question.question].response}
                    onChange={(value, id) => {
                        const tmp = {}
                        tmp[question.question] = {
                            response: value,
                            question: {id: question.id, type: question.type},
                        }
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
                        <Button onClick={submitAnswers}>Submit</Button>
                    </FormLayout>
                </Form>
            )
        } else return <p>LOADING</p>
    }

    return surveyMarkup(survey);
}
