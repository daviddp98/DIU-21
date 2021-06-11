import React, { Component } from "react";
import { BrowserRouter as Router } from "react-router-dom";
import { Route, Switch } from "react-router-dom";
import firebase from 'firebase';
import 'firebaseui/dist/firebaseui.css'

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

import ContactosList from "./components/contactos-list.component";
import ContactosListOut from "./components/contactos-listOut.component";
import TutorialsList from "./components/tutoriales-list.component";
import Header from "./components/header.component";
import SignIn from "./components/SignIn";
import SignInAdmin from "./components/SingInAdmin";


class App extends Component {
  constructor() {
    super()
    this.state = {
      messages: [],
      user: null,
      count: 0,
    }
  }

  componentDidMount() {
    firebase.auth().onAuthStateChanged(user => {
      if (user) {
        this.setState({ user: user })
      } else {
        this.setState({ user: null })
      }
    })
  }

  handleAuth() {
    const provider = new firebase.auth.GoogleAuthProvider()

    firebase.auth().signInWithEmailAndPassword(provider)
      .then(result => console.log(`${result.user.email} ha iniciado sesión`))
      .catch(error => console.error(`Error: ${error.code}: ${error.message}`))
  }

  handleLogout() {
    firebase.auth().signOut()
      .then(() => console.log('Te has desconectado'))
      .catch(error => console.error(`Error: ${error.code}: ${error.message}`))
  }

  render() {
    return (
      this.state.user ?
        <Router>
          <div>
            <Header
              user={this.state.user}
              onAuth={this.handleAuth.bind(this)}
              onLogout={this.handleLogout.bind(this)}
            />

            <div className="container mt-3">
              <h2>AGENDA</h2>
              <Switch>
                <Route exact path={["/", "/contactos"]} component={ContactosList} />
                <Route exact path={["/", "/tutoriales"]} component={TutorialsList} />
                <SignInAdmin exact path="/loginAdmin" />
                <SignIn exact path="/login" />
              </Switch>
            </div>
          </div>
        </Router>
        :
        <Router>
          <div>
            <Header
              user={this.state.user}
              onAuth={this.handleAuth.bind(this)}
              onLogout={this.handleLogout.bind(this)}
            />

            <div className="container mt-3">
              <h2>AGENDA</h2>
              <Switch>
                <Route exact path={["/", "/contactos"]} component={ContactosListOut} />
                <SignIn exact path="/login" />
              </Switch>
            </div>
          </div>
        </Router>
    );
  }
}

export default App;