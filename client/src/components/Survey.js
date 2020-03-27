import React, {useEffect, useState} from "react";
import {Button, Form, Select, RangeSlider, FormLayout, TextField} from '@shopify/polaris';

export function Survey({ survey = {} }) {
    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        setQuestions(survey.questions || [])
        // eslint-disable-next-line
    }, [survey])

    async function submitAnswers() {
      const res = await fetch(`/api/answers`, {
        method: "POST",
        headers: {
          "Accept": "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(questions)
      });

      await res
        .json()
        .then(res => console.log(res))
        .catch(err => console.log(err));
    }

    const questionMarkup = (question, index) => {
        switch(question.type) {
          case "range":
            return (
                <RangeSlider
                    value={question.answers[0]?.response || question.min}
                    label={question.question}
                    onChange={(value, id) => {
                      questions[index].answers = [{val: value}]
                      setQuestions(questions)
                    }}
                    output
                />
            )
          case "option":
            return (
                <Select
                    value={question.answers[0]?.response}
                    label={question.question}
                    options={question.options.map((option) => {
                        return {label: option, value: option}
                    })}
                    onChange={(value, id) => {
                      questions[index].answers = [{response: value}]
                      setQuestions(questions)
                    }}
                />
            )
          case "text":
            return (
                <TextField
                    value={question.answers[0]?.response}
                    onChange={(value, id) => {
                      questions[index].answers = [{response: value}]
                      setQuestions(questions)
                    }}
                    label={question.question}
                    type="text"
                />
            )
          default:
            console.log("invalid question type")
        }
    }

    const surveyMarkup = (survey) => {
        if(survey.questions) {
            const questions = survey.questions.map((question, index) => {
                return questionMarkup(question, index)
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
