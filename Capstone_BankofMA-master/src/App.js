import React, { Component } from "react";
import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Home from "./pages";
import SignInPage from "./pages/signIn";
import UserComponent from "./components/User/UserComponent";

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path="/" component={() => <Home />} exact />
          <Route path="/signIn" component={() => <SignInPage />} exact />
          <Route path="/myAccounts" component={() =><UserComponent />} exact />
        </Switch>
      </Router>
     
    </div>
  );
}

export default App;
