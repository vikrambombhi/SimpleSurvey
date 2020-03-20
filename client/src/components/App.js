import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import enTranslations from "@shopify/polaris/locales/en.json";
import { AppProvider } from "@shopify/polaris";
import { CreateSurveys, Home, CompleteSurveys, FillSurvey } from "./pages";

export function App() {
  return (
    <AppProvider i18n={enTranslations}>
      <Router>
        <Switch>
          <Route path="/app/admin">
            <CreateSurveys admin={true} />
          </Route>
          <Route path="/app/surveys">
            <CompleteSurveys />
          </Route>
          <Route path="/app/survey/:id">
            <FillSurvey />
          </Route>
          <Route path="/app">
            <Home />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
      </Router>
    </AppProvider>
  );
}
