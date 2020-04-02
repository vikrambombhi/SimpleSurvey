import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import enTranslations from "@shopify/polaris/locales/en.json";
import { AppProvider } from "@shopify/polaris";
import {
  AdminDashboard,
  Home,
  CompleteSurveys,
  NewSurvey,
  FillSurvey,
  EditSurvey,
  ReviewSurvey
} from "./pages";

export function App() {
  return (
    <AppProvider i18n={enTranslations}>
      <Router>
        <Switch>
          <Route path="/app/admin/surveys/new">
            <NewSurvey />
          </Route>
          <Route path="/app/admin/surveys/edit/:id">
            <EditSurvey />
          </Route>
          <Route path="/app/admin/surveys/review/:id">
            <ReviewSurvey />
          </Route>
          <Route path="/app/admin/surveys">
            <AdminDashboard />
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
