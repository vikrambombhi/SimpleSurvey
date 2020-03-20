import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { Page } from "@shopify/polaris";
import { Survey } from "../Survey";

export function FillSurvey() {
    let { id } = useParams();
    const [survey, setSurvey] = useState({});

    useEffect(() => {
        async function fetchData() {
            const res = await fetch(`/api/surveys/` + id);
            res
                .json()
                .then(res => setSurvey(res || {}))
                .catch(err => setSurvey({}));
        }

        fetchData();
    }, [id]);

    return (
        <Page
            title="Simple Survey"
            breadcrumbs={[{ content: "Home", url: "/app" }]}
        >
            <Survey survey={survey} />
        </Page>
    );
}
