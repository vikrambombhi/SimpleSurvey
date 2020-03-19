import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import enTranslations from "@shopify/polaris/locales/en.json";
import { AppProvider } from "@shopify/polaris";
import { CreateSurveys, Home, CompleteSurveys } from "./pages";

export function App() {
  return (
    <AppProvider i18n={enTranslations}>
      <Router>
        <Switch>
          <Route path="/admin">
            <CreateSurveys admin={true} />
          </Route>
          <Route path="/surveys">
            <CompleteSurveys />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
      </Router>
    </AppProvider>
  );
}
